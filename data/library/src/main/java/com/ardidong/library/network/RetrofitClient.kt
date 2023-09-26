package com.ardidong.library.network

import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    private val retrofit: Retrofit
) : NetworkClient {
    override suspend fun <T : Any> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}