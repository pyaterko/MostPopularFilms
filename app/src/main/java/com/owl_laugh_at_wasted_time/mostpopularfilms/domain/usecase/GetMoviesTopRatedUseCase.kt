package com.owl_laugh_at_wasted_time.mostpopularfilms.domain.usecase

import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.repository.MovieRepository
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun execute(adult: Boolean = true, page: Int): AppState<MoviesResponse> =
        repository.getMoviesTopRated(adult, page)
}