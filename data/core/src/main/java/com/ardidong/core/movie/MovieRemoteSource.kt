package com.ardidong.core.movie

import com.ardidong.core.movie.model.DiscoverMovieResponse
import com.ardidong.core.movie.model.MovieDetailResponse
import com.ardidong.core.movie.model.MovieReviewResponse
import com.ardidong.core.movie.model.MovieVideoResponse
import com.ardidong.domain.common.ResultOf

interface MovieRemoteSource {

    suspend fun getMoviesByGenres(genres: List<Int>, page: Int): ResultOf<DiscoverMovieResponse>

    suspend fun searchMovie(query: String, page: Int): ResultOf<DiscoverMovieResponse>

    suspend fun getMovieDetail(movieId: Int): ResultOf<MovieDetailResponse>

    suspend fun getMovieReview(movieId: Int): ResultOf<MovieReviewResponse>

    suspend fun getMovieVideo(movieId: Int): ResultOf<MovieVideoResponse>
}