package com.ardidong.core.movie.service

import com.ardidong.core.movie.model.DiscoverMovieResponse
import com.ardidong.core.movie.model.MovieDetailResponse
import com.ardidong.core.movie.model.MovieReviewResponse
import com.ardidong.core.movie.model.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genres: String,
        @Query("page") page: Int = 1
    ): Response<DiscoverMovieResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path(value = "movieId") movieId: Int,
    ): Response<MovieDetailResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReview(
        @Path(value = "movieId") movieId: Int,
    ): Response<MovieReviewResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideo(
        @Path(value = "movieId") movieId: Int,
    ): Response<MovieVideoResponse>

    @GET("search/movie?")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Response<DiscoverMovieResponse>
}