package com.ardidong.library.network

import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import retrofit2.HttpException
import retrofit2.Response

suspend fun<T: Any> handleApi(
    execute: suspend () -> Response<T>
): ResultOf<T>{
    return try {
        val response = execute()
        val body = response.body()

        if (response.isSuccessful && body != null){
            ResultOf.Success(body)
        }else{
            ResultOf.Failure(ErrorEntity.ApiResponseError(message = response.message(), errorCode = response.code().toString()))
        }

    }catch (e: HttpException){
        ResultOf.Failure(ErrorEntity.ApiResponseError(message = e.message(), errorCode = e.code().toString()))
    }catch (t: Throwable){
        ResultOf.Failure(ErrorEntity.ApiExceptionError(message = t.message.orEmpty(), t))
    }
}