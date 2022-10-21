package com.owl_laugh_at_wasted_time.mostpopularfilms.data.api


import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.ActorsResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MovieResponse
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.entity.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/top_rated?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
    fun getMoviesTopRatedAsync(
        @Query("include_adult") adult: Boolean,
        @Query("page") page: Int
    ): Deferred<MoviesResponse>

    @GET("movie/{movie_id}?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
    fun getMovieDetailByIdAsync(@Path("movie_id") movieId: Int): Deferred<MovieResponse>

    @GET("movie/{movie_id}/casts?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
    fun getActorsListAsync(@Path("movie_id") movieId: Int): Deferred<ActorsResponse>

}