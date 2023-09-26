package com.ardidong.domain.movie

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.MovieVideo
import javax.inject.Inject

class GetMovieVideoUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovieVideoUseCase {
    override suspend fun invoke(movieId: Int): ResultOf<List<MovieVideo>> {
        return repository.getMovieVideo(movieId)
    }

}