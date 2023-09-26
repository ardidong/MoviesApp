package com.ardidong.core.di

import com.ardidong.core.movie.MovieRemoteSource
import com.ardidong.core.movie.MovieRemoteSourceImpl
import com.ardidong.core.movie.MovieRepositoryImpl
import com.ardidong.domain.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDataModule {
    @Binds
    abstract fun bindMovieRemoteDataSource(
        remote: MovieRemoteSourceImpl
    ): MovieRemoteSource

    @Binds
    abstract fun bindMovieRepository(
        repository: MovieRepositoryImpl
    ): MovieRepository
}