package com.ardidong.domain.movie.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesByGenreUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMoviesByGenreUseCase {
    override suspend fun invoke(genre: List<Int>): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = {
                MoviePagingSource(repository = repository, genres = genre)
            }
        ).flow
    }

    companion object{
        const val PAGE_SIZE = 20
        const val INITIAL_PAGE_KEY = 1
    }
}