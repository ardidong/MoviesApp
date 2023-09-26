package com.ardidong.domain.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.model.MovieReview

class ReviewPagingSource(
    private val repository: MovieRepository,
    private val movieId: Int
) : PagingSource<Int, MovieReview>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
        return try {
            val currentPageNumber = params.key ?: 1

            val reviews = repository.getMovieReview(
                page = currentPageNumber,
                movieId = movieId
            ).fold(
                success = {it},
                failure = { return LoadResult.Error(RuntimeException()) }
            )

            val nextKey = when {
                currentPageNumber < reviews.totalPages -> currentPageNumber + 1
                else -> null
            }

            return LoadResult.Page(
                prevKey = if (currentPageNumber == 1) null else currentPageNumber - 1,
                nextKey = nextKey,
                data = reviews.results
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}