package com.ardidong.domain.movie

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.MovieDetail
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetMovieDetailUseCase {
    override suspend fun invoke(movieId: Int): ResultOf<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }
}