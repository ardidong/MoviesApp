package com.ardidong.core

import com.ardidong.core.fake.GenreDummyData
import com.ardidong.core.genre.GenreRemoteDataSource
import com.ardidong.core.genre.GenreRemoteDataSourceImpl
import com.ardidong.core.genre.service.GenreApiService
import com.ardidong.domain.common.ResultOf
import com.ardidong.library.network.NetworkClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GenreRemoteSourceTest {

    @MockK
    lateinit var networkClient: NetworkClient

    @MockK
    private lateinit var mockApiService: GenreApiService
    private lateinit var remoteSource: GenreRemoteDataSource
    @Before
    fun setup(){
        MockKAnnotations.init(this)
        networkClient = mockk()
        mockApiService = mockk()

        remoteSource = GenreRemoteDataSourceImpl(networkClient)
        coEvery { networkClient.create(GenreApiService::class.java) } returns mockApiService
    }

    @Test
    fun getGenreList_success_returnResultOfSuccessGenreListResponse() = runBlocking {
        val expectedResponse = GenreDummyData.getGenreList()
        coEvery { mockApiService.getGenreList() } coAnswers {
            Response.success(expectedResponse)
        }

        val result = remoteSource.getGenreList()

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
    }

    @Test
    fun getGenreList_failed_returnResultOfFailure() = runBlocking {
        val errorResponse = "{\n" +
                "    \"status_code\": 500,\n" +
                "    \"status_message\": \"Internal Server Error\",\n" +
                "    \"success\": false\n" +
                "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())

        coEvery { mockApiService.getGenreList() } coAnswers {
            Response.error(500, errorResponseBody)
        }

        val result = remoteSource.getGenreList()

        assertTrue(result is ResultOf.Failure)
    }

}