package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.ItemActorBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.utils.click

class ActorViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemActorBinding by viewBinding()

    fun bind(actor: ActorsResponse.Cast, delegate: ActorAdapter.Delegate?) {
        with(viewBinding) {
            name.text = actor.name
            character.text = actor.character
            Glide.with(poster)
                .load(BuildConfig.MOVIE_POSTER_PATH.plus(actor.profilePath))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(IMAGE_RADIUS)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transition(DrawableTransitionOptions().crossFade(DELAY))
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(poster)
            viewBinding.root.click { delegate?.onItemClick(actor) }
        }
    }

    companion object {
        private const val DELAY = 800
        private const val IMAGE_RADIUS = 18
    }
}