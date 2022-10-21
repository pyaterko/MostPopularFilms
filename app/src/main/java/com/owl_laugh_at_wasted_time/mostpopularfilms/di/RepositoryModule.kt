package com.owl_laugh_at_wasted_time.mostpopularfilms.di

import com.owl_laugh_at_wasted_time.mostpopularfilms.data.MovieRepositoryImpl
import com.owl_laugh_at_wasted_time.mostpopularfilms.data.datasource.RemoteDataSource
import com.owl_laugh_at_wasted_time.mostpopularfilms.data.datasource.RemoteDataSourceImpl
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

}