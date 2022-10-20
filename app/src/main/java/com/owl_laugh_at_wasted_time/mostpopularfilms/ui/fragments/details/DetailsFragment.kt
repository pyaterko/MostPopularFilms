package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.FragmentDetailsBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details.adapter.ActorAdapter

class DetailsFragment : Fragment(R.layout.fragment_details), ActorAdapter.Delegate {

    private val adapter by lazy { ActorAdapter(this) }
    private val binding by viewBinding(FragmentDetailsBinding::bind)

    val list: ArrayList<ActorsResponse.Cast> = arrayListOf(
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
        ActorsResponse.Cast(
            true, 1000, "dfdsf", "yjyu", 55, 22, "fggf",
            "ggggggg", 22, "llll", 2.3, "ooooo"
        ),
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvActorsFoto.also {
            it.adapter = adapter
            adapter.setItems(list)
        }
    }

    override fun onItemClick(actor: ActorsResponse.Cast) {

    }


}