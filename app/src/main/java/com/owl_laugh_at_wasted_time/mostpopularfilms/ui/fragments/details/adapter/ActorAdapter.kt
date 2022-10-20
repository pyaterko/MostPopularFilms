package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.R

class ActorAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<ActorViewHolder?>() {

    interface Delegate {
        fun onItemClick(actor: ActorsResponse.Cast)
    }

    private val data = ArrayList<ActorsResponse.Cast>()

    fun setItems(newList: List<ActorsResponse.Cast>) {
        val result = DiffUtil.calculateDiff(
            DiffUtilCallback(
                data,
                newList as ArrayList<ActorsResponse.Cast>
            )
        )
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_actor,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(data[position], delegate)

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<ActorsResponse.Cast>,
        private var newItems: ArrayList<ActorsResponse.Cast>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}