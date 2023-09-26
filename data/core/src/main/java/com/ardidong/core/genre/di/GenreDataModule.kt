package com.ardidong.core.genre.di

import com.ardidong.core.genre.GenreRemoteDataSource
import com.ardidong.core.genre.GenreRemoteDataSourceImpl
import com.ardidong.core.genre.GenreRepositoryImpl
import com.ardidong.domain.genre.GenreRepository
import dagger.Binds
import dagger.Module

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GenreDataModule {

    @Binds
    abstract fun bindGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository

    @Binds
    abstract fun bindGenreRemoteDataSource(
        genreRemoteDataSourceImpl: GenreRemoteDataSourceImpl
    ): GenreRemoteDataSource
}
