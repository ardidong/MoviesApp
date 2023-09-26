package com.ardidong.domain.movie

import androidx.paging.PagingData
import com.ardidong.domain.movie.model.MovieReview
import kotlinx.coroutines.flow.Flow

interface GetMovieReviewUseCase {
    suspend operator fun invoke(movieId: Int): Flow<PagingData<MovieReview>>
}