package com.ardidong.domain.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.model.Movie

class MoviePagingSource(
    private val repository: MovieRepository,
    private val genres : List<Int>
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPageNumber = params.key ?: 1

            val movies = repository.getMoviesByGenre(
                page = currentPageNumber,
                genres = genres

            ).fold(
                success = {it},
                failure = { return LoadResult.Error(RuntimeException()) }
            )

            val nextKey = when {
                currentPageNumber < movies.totalPages -> currentPageNumber + 1
                else -> null
            }

            return LoadResult.Page(
                prevKey = null,
                nextKey = nextKey,
                data = movies.results
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}