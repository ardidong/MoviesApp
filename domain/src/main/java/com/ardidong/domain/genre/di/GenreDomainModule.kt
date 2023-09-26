package com.ardidong.domain.genre.di

import com.ardidong.domain.genre.GetGenreListUseCase
import com.ardidong.domain.genre.GetGenreListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GenreDomainModule {

    @Binds
    abstract fun bindsGetGenreListUseCase(
        getGenreListUseCaseImpl: GetGenreListUseCaseImpl
    ): GetGenreListUseCase
}