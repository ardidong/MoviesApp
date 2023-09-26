package com.ardidong.domain.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.domain.movie.paging.ReviewPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieReviewUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovieReviewUseCase {
    override suspend fun invoke(movieId: Int): Flow<PagingData<MovieReview>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            initialKey  = INITIAL_PAGE_KEY,
            pagingSourceFactory = {
                ReviewPagingSource(repository, movieId)
            }
        ).flow
    }

    companion object{
        const val PAGE_SIZE = 20
        const val INITIAL_PAGE_KEY = 1
    }
}