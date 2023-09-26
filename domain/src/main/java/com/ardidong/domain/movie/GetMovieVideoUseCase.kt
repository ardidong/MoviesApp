package com.ardidong.domain.movie

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.MovieVideo

interface GetMovieVideoUseCase {
    suspend operator fun invoke(movieId: Int): ResultOf<List<MovieVideo>>
}