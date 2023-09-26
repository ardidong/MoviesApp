package com.ardidong.core.genre

import com.ardidong.core.genre.model.GenreListResponse
import com.ardidong.core.genre.service.GenreApiService
import com.ardidong.domain.common.ResultOf
import com.ardidong.library.network.NetworkClient
import com.ardidong.library.network.handleApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val client: NetworkClient
) : GenreRemoteDataSource {
    override suspend fun getGenreList(): ResultOf<GenreListResponse> {
        delay(1200)
        val call = client.create(GenreApiService::class.java)
        return handleApi { call.getGenreList() }
    }
}