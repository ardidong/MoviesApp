package com.ardidong.domain.movie.list

import androidx.paging.PagingData
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesByGenreUseCase {
    suspend operator fun invoke(genre: List<Int>): Flow<PagingData<Movie>>
}