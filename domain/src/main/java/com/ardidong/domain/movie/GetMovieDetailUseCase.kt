package com.ardidong.domain.movie

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.MovieDetail

interface GetMovieDetailUseCase {
    suspend operator fun invoke(movieId: Int) : ResultOf<MovieDetail>
}