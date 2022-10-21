package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.owl_laugh_at_wasted_time.list_screen.fragment.adapter.MovieAdapter
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.FragmentListFilmsBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.showSnakeBar

class ListFilmFragment : BaseFragment(R.layout.fragment_list_films), MovieAdapter.Delegate {

    private val adapter by lazy { MovieAdapter(this) }
    private val binding by viewBinding(FragmentListFilmsBinding::bind)
    private val viewModel by viewModels<ListFilmsViewModel> { viewModelFactory }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }
//    val list: ArrayList<MoviesResponse.Movie> = arrayListOf(
//        MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "10.8.22",
//            "Король Лев (1994)",
//            true,
//            9.1,
//            1
//
//        ),
//        MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "",
//            "Король Лев (1994)",
//            true,
//            9.1,
//            1
//
//        ),
//        MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "",
//            "Король Лев (1994)",
//            true,
//            9.1,
//            1
//
//        ),
//        MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "",
//            "Король Лев (1994)",
//            true,
//            9.1,
//            1
//
//        ),
//        MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "",
//            "Король Лев (1994)",
//            true,
//            9.1,
//            1
//
//        )
//    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListFilm.also {
            it.adapter = adapter
            it.setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesTopRated(true)
    }

    override fun onItemClick(movie: MoviesResponse.Movie) {
        findNavController().navigate(ListFilmFragmentDirections.actionListFilmFragmentToDetailsFragment())
    }


    override fun getMoreMovies() {
        viewModel.getMoviesTopRated(true)
    }

    override fun initListeners() {}

    override fun initObservers() {
        viewModel.moviesLiveData
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }
    }


    override fun renderSuccess(result: AppState.Success<*>) {
        showLoading(false)
        when (val moveResponse = result.data) {
            is MoviesResponse -> {
                renderMovies(moveResponse)
            }
        }
    }

    private fun renderMovies(moveResponse: MoviesResponse) {
        adapter.setItems(moveResponse.movies)
        if (adapter.itemCount > ZERO_VALUE)
            viewModel.setCurrentPage(moveResponse.page, moveResponse.totalPages)
    }

    override fun showLoading(isShow: Boolean) {
        binding.progress.isVisible = isShow
    }

    override fun showError(throwable: Throwable) {
        binding.root.showSnakeBar(throwable.localizedMessage)
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val ONE_VALUE = 1
        private const val TWO_VALUE = 2
        private const val INPUT_METHOD_MANAGER_FLAGS = 0
    }
}