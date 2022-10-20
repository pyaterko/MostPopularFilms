package com.owl_laugh_at_wasted_time.list_screen.fragment.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.owl_laugh_at_wasted_time.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.ItemFilmBinding
import java.text.SimpleDateFormat
import java.util.*
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

fun getColorByValue(value: Int): Int =
    try {
        when (value) {
            in 0..25 -> Color.parseColor("#FF0000")
            in 26..50 -> Color.parseColor("#FF8C00")
            in 51..75 -> Color.parseColor("#FFFF00")
            else -> Color.parseColor("#00FF00")
        }
    } catch (err: Exception) {
        Log.d("VVVV", "${err.localizedMessage}")
        0
    }

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun releaseDateToString(date: String): String? {
    return try {
        var result: String = ""
        toDateString(date)?.let { date ->
            date.split(" ").forEach {
                result += it.replaceFirstChar(Char::titlecase) + " "
            }
        }
        result
    } catch (err: Exception) {
        null
    }
}

fun toDateString(value: String): String? =
    try {
        SimpleDateFormat(DATE_FORMAT_IN, Locale.getDefault())
            .parse(value)?.let { date ->
                SimpleDateFormat(DATE_FORMAT_OUT, Locale.getDefault()).format(date)
            }
    } catch (err: Exception) {
        null
    }


const val DATE_FORMAT_IN = "yyyy-MM-dd"
const val DATE_FORMAT_OUT = "dd MMMM yyyy"
const val TIME_STRING_TEMPLATE = "%sч %sм"