package com.ardidong.domain.movie.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : SearchMoviesUseCase {
    override suspend fun invoke(query: String, genre: List<Int>): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(GetMoviesByGenreUseCaseImpl.PAGE_SIZE),
            initialKey = GetMoviesByGenreUseCaseImpl.INITIAL_PAGE_KEY,
            pagingSourceFactory = {
                MoviePagingSource(
                    repository = repository,
                    genres = genre,
                    query = query.replace(" ", "+")
                )
            }
        ).flow
    }
}