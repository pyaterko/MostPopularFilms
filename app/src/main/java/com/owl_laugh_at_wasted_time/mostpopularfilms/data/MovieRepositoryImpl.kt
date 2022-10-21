package com.owl_laugh_at_wasted_time.mostpopularfilms.data

import com.owl_laugh_at_wasted_time.mostpopularfilms.data.datasource.RemoteDataSource
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.repository.MovieRepository
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getMoviesTopRated(adult: Boolean, page: Int): AppState<MoviesResponse> =
        dataSource.getMoviesTopRated(adult, page)

    override suspend fun getMovieDetailById(movieId: Int): AppState<MovieResponse> =
        dataSource.getMovieDetailById(movieId)

    override suspend fun getActorsList(movieId: Int): AppState<ActorsResponse> =
        dataSource.getActorsList(movieId)

}