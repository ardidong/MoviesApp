package com.ardidong.core.genre

import com.ardidong.core.genre.model.GenreListResponse
import com.ardidong.domain.common.ResultOf

interface GenreRemoteDataSource {
    suspend fun getGenreList(): ResultOf<GenreListResponse>
}