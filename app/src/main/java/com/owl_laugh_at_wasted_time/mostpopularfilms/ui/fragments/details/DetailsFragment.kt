package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.FragmentDetailsBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details.adapter.ActorAdapter
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.showSnakeBar
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.toDateString

class DetailsFragment : BaseFragment(R.layout.fragment_details), ActorAdapter.Delegate {

    private val adapter by lazy { ActorAdapter(this) }
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvActorsFoto.also {
            it.adapter = adapter
            it.setHasFixedSize(true)
        }
        args.movieId.let {
            viewModel.getMovieDetail(it)
            viewModel.getActors(it)
        }
    }

    override fun initListeners() {}
    override fun onItemClick(actor: ActorsResponse.Cast) {}

    override fun initObservers() {
        viewModel.movieLiveData
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }

        viewModel.actorLiveData
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }
    }

    override fun renderSuccess(result: AppState.Success<*>) {
        showLoading(false)
        when (val resultResponse = result.data) {
            is MovieResponse -> renderMovie(resultResponse)
            is ActorsResponse -> adapter.setItems(resultResponse.cast)
        }
    }

    override fun showLoading(isShow: Boolean) {
        binding.progressDetails.isVisible = isShow
    }

    override fun showError(throwable: Throwable) {
        throwable.localizedMessage?.let { binding.root.showSnakeBar(it) }
    }

    private fun renderMovie(resultResponse: MovieResponse) {
        with(binding) {
            this.tvPlaylistTitle.text = resultResponse.title
            Glide.with(ivAlbum)
                .load(BuildConfig.MOVIE_POSTER_PATH.plus(resultResponse.backdropPath))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(IMAGE_RADIUS)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transition(DrawableTransitionOptions().crossFade(DELAY))
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(ivAlbum)

            resultResponse.releaseDate.let { it ->
                toDateString(it)?.let {
                    if (it.isNotEmpty()) {
                        textView5.text = it
                    }
                }
            }
            resultResponse.overview?.let {
                tvDescription.text = it
                titleDescription.isVisible = it.isNotEmpty()
            }
        }
    }

    companion object {
        private const val IMAGE_RADIUS = 18
        const val DELAY = 800
    }
}