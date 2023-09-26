package com.ardidong.domain.movie.di

import com.ardidong.domain.movie.GetMovieDetailUseCase
import com.ardidong.domain.movie.GetMovieDetailUseCaseImpl
import com.ardidong.domain.movie.GetMovieReviewUseCase
import com.ardidong.domain.movie.GetMovieReviewUseCaseImpl
import com.ardidong.domain.movie.GetMovieVideoUseCase
import com.ardidong.domain.movie.GetMovieVideoUseCaseImpl
import com.ardidong.domain.movie.GetMoviesByGenreUseCase
import com.ardidong.domain.movie.GetMoviesByGenreUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDomainModule {
    @Binds
    abstract fun bindGetMoviesByGenre(
        useCase: GetMoviesByGenreUseCaseImpl
    ): GetMoviesByGenreUseCase

    @Binds
    abstract fun bindGetMovieDetail(
        useCase: GetMovieDetailUseCaseImpl
    ): GetMovieDetailUseCase

    @Binds
    abstract fun bindGetMovieReview(
        useCase: GetMovieReviewUseCaseImpl
    ): GetMovieReviewUseCase

    @Binds
    abstract fun bindGetMovieVideo(
        useCase: GetMovieVideoUseCaseImpl
    ): GetMovieVideoUseCase
}