package com.owl_laugh_at_wasted_time.mostpopularfilms.data.datasource

import com.owl_laugh_at_wasted_time.mostpopularfilms.data.api.MovieApi
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val movieApi: MovieApi) : RemoteDataSource {
    override suspend fun getMoviesTopRated(adult: Boolean, page: Int): AppState<MoviesResponse> =
        try {
            val result = movieApi.getMoviesTopRatedAsync(adult, page).await()

            if (result.movies.isNotEmpty()) {
                AppState.Success(result)
            } else {
                AppState.Error(Exception(NO_DATA))
            }
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMovieDetailById(movieId: Int): AppState<MovieResponse> =
        try {
            val result = movieApi.getMovieDetailByIdAsync(movieId).await()
            AppState.Success(result)
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getActorsList(movieId: Int): AppState<ActorsResponse> =
        try {
            val result = movieApi.getActorsListAsync(movieId).await()

            if (result.cast.isNotEmpty()) {
                AppState.Success(result)
            } else {
                AppState.Error(Exception(NO_DATA))
            }
        } catch (err: Exception) {
            AppState.Error(err)
        }



    companion object {
        const val NO_DATA = "Отсутствуют данные"
    }
}