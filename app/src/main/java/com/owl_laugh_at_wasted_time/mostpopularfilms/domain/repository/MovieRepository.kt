package com.owl_laugh_at_wasted_time.mostpopularfilms.domain.repository

import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState

interface MovieRepository {
    suspend fun getMoviesTopRated(adult: Boolean, page: Int): AppState<MoviesResponse>
    suspend fun getMovieDetailById(movieId: Int): AppState<MovieResponse>
    suspend fun getActorsList(movieId: Int): AppState<ActorsResponse>
}