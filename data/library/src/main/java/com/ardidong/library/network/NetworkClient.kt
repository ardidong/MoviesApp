package com.ardidong.library.network

interface NetworkClient {

    suspend fun <T: Any> create(clazz: Class<T>) : T

}