package com.ardidong.core.movie

import com.ardidong.core.movie.model.DiscoverMovieResponse
import com.ardidong.core.movie.model.MovieDetailResponse
import com.ardidong.core.movie.model.MovieReviewResponse
import com.ardidong.core.movie.model.MovieVideoResponse
import com.ardidong.core.movie.service.MovieApiService
import com.ardidong.domain.common.ResultOf
import com.ardidong.library.network.NetworkClient
import com.ardidong.library.network.handleApi
import javax.inject.Inject

class MovieRemoteSourceImpl @Inject constructor(
    private val networkClient: NetworkClient
) : MovieRemoteSource {

    override suspend fun getMoviesByGenres(genres: List<Int>, page: Int): ResultOf<DiscoverMovieResponse> {
        val service = networkClient.create(MovieApiService::class.java)
        return handleApi { service.getMoviesByGenre(genres = genres.joinToString(","), page = page) }
    }

    override suspend fun getMovieDetail(movieId: Int): ResultOf<MovieDetailResponse> {
        val service = networkClient.create(MovieApiService::class.java)
        return handleApi { service.getMovieDetail(movieId) }
    }

    override suspend fun getMovieReview(movieId: Int): ResultOf<MovieReviewResponse> {
        val service = networkClient.create(MovieApiService::class.java)
        return handleApi { service.getMovieReview(movieId) }
    }

    override suspend fun getMovieVideo(movieId: Int): ResultOf<MovieVideoResponse> {
        val service = networkClient.create(MovieApiService::class.java)
        return handleApi { service.getMovieVideo(movieId) }
    }
}