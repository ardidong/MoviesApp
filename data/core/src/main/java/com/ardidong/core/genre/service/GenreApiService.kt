package com.ardidong.core.genre.service

import com.ardidong.core.genre.model.GenreListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApiService {
    @GET("genre/movie/list")
    suspend fun getGenreList(): Response<GenreListResponse>

}