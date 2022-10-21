package com.owl_laugh_at_wasted_time.mostpopularfilms.di

import android.os.Build
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.owl_laugh_at_wasted_time.mostpopularfilms.BuildConfig
import com.owl_laugh_at_wasted_time.mostpopularfilms.data.api.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
class ApiModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val client = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(10))
            .connectTimeout(Duration.ofSeconds(10))
            .readTimeout(Duration.ofSeconds(10))
            .writeTimeout(Duration.ofSeconds(10))
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun initApiWorker() = Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_BASE_URL)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)


}