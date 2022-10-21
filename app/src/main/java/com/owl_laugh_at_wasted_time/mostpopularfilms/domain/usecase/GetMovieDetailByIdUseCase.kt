package com.owl_laugh_at_wasted_time.mostpopularfilms.domain.usecase

import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.repository.MovieRepository
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int): AppState<MovieResponse> =
        repository.getMovieDetailById(movieId)
}