package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.usecase.GetMoviesTopRatedUseCase
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListFilmsViewModel @Inject constructor(
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<AppState<MoviesResponse>>()
    val moviesLiveData: LiveData<AppState<MoviesResponse>> = _moviesLiveData

    private val allMovies: ArrayList<MoviesResponse.Movie> = arrayListOf()

    private var currentPage: Int = ONE_VALUE

    fun setCurrentPage(value: Int, totalPage: Int) {
        if (value < totalPage) {
            currentPage = value.plus(ONE_VALUE)
        }
    }

    override fun handleError(throwable: Throwable) {}


    fun getMoviesTopRated(adult: Boolean = false, page: Int = currentPage): Job =
        viewModelScopeCoroutine.launch {
            _moviesLiveData.postValue(AppState.Loading)
            val movies = getMoviesTopRatedUseCase.execute(adult, page)
            if (movies is AppState.Success) {
                when (val moviesList = movies.data) {
                    is MoviesResponse -> {
                        allMovies.addAll(moviesList.movies)
                        _moviesLiveData.postValue(
                            AppState.Success<MoviesResponse>(moviesList.copy(movies = allMovies))
                        )
                    }
                }
            }
        }


    companion object {
        private const val ONE_VALUE = 1
    }
}