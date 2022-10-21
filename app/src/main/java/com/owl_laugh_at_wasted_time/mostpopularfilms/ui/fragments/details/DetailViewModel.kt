package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.usecase.GetActorsUseCase
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.usecase.GetMovieDetailByIdUseCase
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailViewModel @Inject constructor(
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase,
    private val getActorsUseCase: GetActorsUseCase,
) : BaseViewModel() {

    private val _movieLiveData = MutableLiveData<AppState<MovieResponse>>()
    val movieLiveData: LiveData<AppState<MovieResponse>> = _movieLiveData

    private val _actorLiveData = MutableLiveData<AppState<ActorsResponse>>()
    val actorLiveData: LiveData<AppState<ActorsResponse>> = _actorLiveData

    override fun handleError(throwable: Throwable) {}

    fun getMovieDetail(movieId: Int): Job =
        viewModelScopeCoroutine.launch {
            _movieLiveData.postValue(AppState.Loading)
            _movieLiveData.postValue(getMovieDetailByIdUseCase.execute(movieId))
        }

    fun getActors(movieId: Int): Job =
        viewModelScopeCoroutine.launch {
            _actorLiveData.postValue(AppState.Loading)
            _actorLiveData.postValue(getActorsUseCase.execute(movieId))
        }
}