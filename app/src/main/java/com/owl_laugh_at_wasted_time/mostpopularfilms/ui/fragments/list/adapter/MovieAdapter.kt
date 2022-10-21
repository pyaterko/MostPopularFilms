package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse

class MovieAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<MovieViewHolder?>() {

    interface Delegate {

        fun onItemClick(movie: MoviesResponse.Movie)

        fun getMoreMovies()
    }

//       для демонстрации работы MotionLayout в приложение без получения MOVIE_API_KEY
//        раскомментируйте код внутри списка
    private val data: ArrayList<MoviesResponse.Movie> = arrayListOf(
//                MoviesResponse.Movie(
//            true,
//            "Onee is proof",
//            emptyList(),
//            2,
//            "",
//            "",
//            "Король Лев (1994) Львенок Симба бросает вызов дяде-убийце.",
//            7.1,
//            null,
//            "1963-03-01",
//            "Король Лев (1994)",
//            true,
//            5.1,
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
//            "Мэверик встречает лейтенанта Брэдли Брэдшоу — сына своего покойного друга, лейтенанта Ника Брэдшоу. ",
//            7.1,
//            null,
//            "2022-07-29",
//            "Топ Ган",
//            true,
//            7.1,
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
//            "Однажды он посвящает своих подопечных в тайну Общества мёртвых поэтов..",
//            6.1,
//            null,
//            "2022-07-29",
//            "Гамильтон",
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
//            " маленький Бродяга пускается на поиски денег",
//            9.1,
//            null,
//            "2022-07-29",
//            "Огни большого города",
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
//            "Джон Китинг - новый преподаватель английской словесности в консервативном американском колледже.",
//            5.3,
//            null,
//            "2022-07-29",
//            "Джон Китинг",
//            true,
//            3.1,
//            1
//
//        )
    )

    fun setItems(newList: List<MoviesResponse.Movie>) {
        val result = DiffUtil.calculateDiff(
            DiffUtilCallback(
                data,
                newList as ArrayList<MoviesResponse.Movie>
            )
        )
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_film,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(data[position], delegate, position, data.size)

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<MoviesResponse.Movie>,
        private var newItems: ArrayList<MoviesResponse.Movie>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}