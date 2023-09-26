package com.ardidong.core.movie

import com.ardidong.core.movie.mapper.MovieDetailMapper
import com.ardidong.core.movie.mapper.MovieMapper
import com.ardidong.core.movie.mapper.MovieReviewMapper
import com.ardidong.core.movie.mapper.MovieVideoMapper
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.model.MovieDetail
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.domain.movie.model.MovieVideo
import com.ardidong.domain.movie.model.PagedData
import kotlinx.coroutines.delay
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteSource: MovieRemoteSource
) : MovieRepository {
    override suspend fun getMoviesByGenre(genres: List<Int>, page: Int): ResultOf<PagedData<Movie>> {
        val mapper = MovieMapper()

        delay(1500)

        return remoteSource.getMoviesByGenres(genres, page).fold(
            success = {
                val data = mapper.toModel(it)
                ResultOf.Success(data)
            },
            failure = {ResultOf.Failure(it)}
        )
    }

    override suspend fun getMovieDetail(movieId: Int): ResultOf<MovieDetail> {
        val mapper = MovieDetailMapper()
        return remoteSource.getMovieDetail(movieId).fold(
            success = { ResultOf.Success(mapper.toModel(it)) },
            failure = { ResultOf.Failure(it) }
        )
    }

    override suspend fun getMovieReview(movieId: Int, page: Int): ResultOf<PagedData<MovieReview>> {
        val mapper = MovieReviewMapper()
        return remoteSource.getMovieReview(movieId).fold(
            success = { ResultOf.Success(mapper.toModel(it)) },
            failure = { ResultOf.Failure(it) }
        )
    }

    override suspend fun getMovieVideo(movieId: Int): ResultOf<List<MovieVideo>> {
        val mapper = MovieVideoMapper()
        return remoteSource.getMovieVideo(movieId).fold(
            success = { ResultOf.Success(mapper.toModel(it)) },
            failure = { ResultOf.Failure(it) }
        )
    }
}