package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.list_screen.fragment.adapter.MovieAdapter
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.databinding.FragmentListFilmsBinding

class ListFilmFragment : Fragment(R.layout.fragment_list_films), MovieAdapter.Delegate {

    private val adapter by lazy { MovieAdapter(this) }
    private val binding by viewBinding(FragmentListFilmsBinding::bind)

    val list: ArrayList<MoviesResponse.Movie> = arrayListOf(
        MoviesResponse.Movie(
            true,
            "Onee is proof",
            emptyList(),
            2,
            "",
            "",
            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
            7.1,
            null,
            "10.8.22",
            "Король Лев (1994)",
            true,
            9.1,
            1

        ),
        MoviesResponse.Movie(
            true,
            "Onee is proof",
            emptyList(),
            2,
            "",
            "",
            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
            7.1,
            null,
            "",
            "Король Лев (1994)",
            true,
            9.1,
            1

        ),
        MoviesResponse.Movie(
            true,
            "Onee is proof",
            emptyList(),
            2,
            "",
            "",
            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
            7.1,
            null,
            "",
            "Король Лев (1994)",
            true,
            9.1,
            1

        ),
        MoviesResponse.Movie(
            true,
            "Onee is proof",
            emptyList(),
            2,
            "",
            "",
            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
            7.1,
            null,
            "",
            "Король Лев (1994)",
            true,
            9.1,
            1

        ),
        MoviesResponse.Movie(
            true,
            "Onee is proof",
            emptyList(),
            2,
            "",
            "",
            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
            7.1,
            null,
            "",
            "Король Лев (1994)",
            true,
            9.1,
            1

        )
    )

    override fun onItemClick(movie: MoviesResponse.Movie) {
        findNavController().navigate(ListFilmFragmentDirections.actionListFilmFragmentToDetailsFragment())

    }

    override fun getMoreMovies() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListFilm.also {
            it.adapter = adapter
            adapter.setItems(list)
        }
    }


}