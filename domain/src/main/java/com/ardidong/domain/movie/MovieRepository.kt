package com.ardidong.domain.movie

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.model.MovieDetail
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.domain.movie.model.MovieVideo
import com.ardidong.domain.movie.model.PagedData

interface MovieRepository {
    suspend fun getMoviesByGenre(genres: List<Int>, page: Int) : ResultOf<PagedData<Movie>>

    suspend fun getMovieReview(movieId: Int, page: Int): ResultOf<PagedData<MovieReview>>

    suspend fun getMovieDetail(movieId: Int): ResultOf<MovieDetail>

    suspend fun getMovieVideo(movieId: Int): ResultOf<List<MovieVideo>>

}