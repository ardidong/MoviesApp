package com.ardidong.domain.movie.list

import androidx.paging.PagingData
import com.ardidong.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase{
    suspend operator fun invoke(query: String, genre: List<Int>): Flow<PagingData<Movie>>
}