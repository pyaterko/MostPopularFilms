package com.owl_laugh_at_wasted_time.list_screen.fragment.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.ItemFilmBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.click
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.getColorByValue
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.releaseDateToString
import kotlin.math.roundToInt

class MovieViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemFilmBinding by viewBinding()

    fun bind(
        movie: MoviesResponse.Movie,
        delegate: MovieAdapter.Delegate?,
        position: Int,
        countItems: Int
    ) {
        with(viewBinding) {
            this.tvTitle.text = movie.title
            tvDescription.text = movie.overview
            val popular = (movie.voteAverage * MULTIPLIER).roundToInt()
            ratingProgress.setProgress(popular, true)
            ratingValue.text = popular.toString()
            ratingProgress.setIndicatorColor(getColorByValue(popular))
            if (movie.releaseDate.isNullOrEmpty().not()) {
                release.text = releaseDateToString(movie.releaseDate)
            }
            Glide.with(ivFilm)
                .load(BuildConfig.MOVIE_POSTER_PATH.plus(movie.posterPath))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(IMAGE_RADIUS)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transition(DrawableTransitionOptions().crossFade(DELAY))
                .placeholder(R.drawable.quin)
                .error(R.drawable.quin)
                .into(ivFilm)
            viewBinding.tvDescription.click { delegate?.onItemClick(movie) }
            if (countItems > ZERO_VALUE && position == countItems - FIVE_VALUE) {
                delegate?.getMoreMovies()
            }
        }
    }

    companion object {
        private const val MULTIPLIER = 10
        private const val IMAGE_RADIUS = 18
        private const val ZERO_VALUE = 0
        private const val FIVE_VALUE = 5
        private const val DELAY = 800
    }
}
