package com.ardidong.core

import com.ardidong.core.fake.FakeMovieData
import com.ardidong.core.movie.MovieRemoteSource
import com.ardidong.core.movie.MovieRemoteSourceImpl
import com.ardidong.core.movie.service.MovieApiService
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

class MovieRemoteSourceTest {

    @MockK
    lateinit var networkClient: NetworkClient

    @MockK
    private lateinit var mockApiService: MovieApiService

    private lateinit var movieRemoteSource: MovieRemoteSource

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        networkClient = mockk()
        mockApiService = mockk()
        movieRemoteSource = MovieRemoteSourceImpl(networkClient)
    }

    @Test
    fun getMoviesByGenre_success_returnFirstPaginationResult() = runBlocking{
        val expectedResponse = FakeMovieData.createDiscoverMovieResponse()
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMoviesByGenre(any(), any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.getMoviesByGenres(listOf(), 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
    }

    @Test
    fun getMoviesByGenre_failed_returnResultFailure() = runBlocking{
        val errorResponseBody = "errorBody".toResponseBody(null)
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMoviesByGenre(any(), any()) } coAnswers {
            Response.error(500, errorResponseBody)
        }

        val result = movieRemoteSource.getMoviesByGenres(listOf(), 1)

        assertTrue(result is ResultOf.Failure)
    }

    @Test
    fun searchMovie_success_returnPaginationResult() = runBlocking{
        val movieTitle = "Expend4bles"
        val expectedResponse = FakeMovieData.createDiscoverMovieResponse(isResultEmpty = true).copy(
            results = listOf( FakeMovieData.createMovieItems().find { it.title == movieTitle })
        )
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.searchMovie(any(), any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.searchMovie(query = movieTitle, page = 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
        assertEquals(movieTitle, result.data.results?.first()?.title)
    }

    @Test
    fun searchMovie_notFound_returnEmptyPaginationResult() = runBlocking{
        val movieTitle = "123456789"
        val expectedResponse = FakeMovieData.createDiscoverMovieResponse(isResultEmpty = true).copy(
            results = FakeMovieData.createMovieItems().find { it.title == movieTitle }?.let {
                listOf(it)
            } ?: emptyList()
        )
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.searchMovie(any(), any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.searchMovie(query = movieTitle, page = 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
        assertTrue(result.data.results?.isEmpty()!!)
    }

    @Test
    fun searchMovie_failed_returnResultOfFailure() = runBlocking{
        val movieTitle = "Elemental"
        val errorResponse = "{\n" +
                "    \"status_code\": 500,\n" +
                "    \"status_message\": \"Internal Server Error\",\n" +
                "    \"success\": false\n" +
                "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.searchMovie(any(), any()) } coAnswers {
            Response.error(500 , errorResponseBody)
        }

        val result = movieRemoteSource.searchMovie(query = movieTitle, page = 1)

        assertTrue(result is ResultOf.Failure)
    }

    @Test
    fun getMovieDetail_success_returnMovieDetailResponse() = runBlocking {
        val expectedResponse = FakeMovieData.createMovieDetailResponse()
        val id = expectedResponse.id!!

        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieDetail(any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.getMovieDetail(id)
        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
    }

    @Test
    fun getMovieDetail_failed_returnResultOfFailure() = runBlocking {
        val id = 1
        val errorResponse = "{\n" +
                "    \"status_code\": 500,\n" +
                "    \"status_message\": \"Internal Server Error\",\n" +
                "    \"success\": false\n" +
                "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieDetail(any()) } coAnswers {
            Response.error(500 , errorResponseBody)
        }

        val result = movieRemoteSource.getMovieDetail(movieId = id)
        assertTrue(result is ResultOf.Failure)
    }

    @Test
    fun getMovieReview_success_returnMovieReviewResponse() = runBlocking {
        val expectedResponse = FakeMovieData.createMovieReviewResponse()
        val id = expectedResponse.id!!

        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieReview(any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.getMovieReview(id)
        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
    }

    @Test
    fun getMovieReview_failed_returnResultOfFailure() = runBlocking {
        val id = 1
        val errorResponse = "{\n" +
                "    \"status_code\": 500,\n" +
                "    \"status_message\": \"Internal Server Error\",\n" +
                "    \"success\": false\n" +
                "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieReview(any()) } coAnswers {
            Response.error(500 , errorResponseBody)
        }

        val result = movieRemoteSource.getMovieReview(movieId = id)
        assertTrue(result is ResultOf.Failure)
    }

    @Test
    fun getMovieVideo_success_returnMovieVideoResponse() = runBlocking {
        val expectedResponse = FakeMovieData.createMovieVideoResponse()
        val id = expectedResponse.id!!

        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieVideo(any()) } coAnswers {
            Response.success(expectedResponse)
        }

        val result = movieRemoteSource.getMovieVideo(id)
        assertTrue(result is ResultOf.Success)
        assertEquals(expectedResponse, (result as ResultOf.Success).data)
    }

    @Test
    fun getMovieVideo_failed_returnResultOfFailure() = runBlocking {
        val id = 1
        val errorResponse = "{\n" +
                "    \"status_code\": 500,\n" +
                "    \"status_message\": \"Internal Server Error\",\n" +
                "    \"success\": false\n" +
                "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { networkClient.create(MovieApiService::class.java) } returns mockApiService
        coEvery { mockApiService.getMovieVideo(any()) } coAnswers {
            Response.error(500 , errorResponseBody)
        }

        val result = movieRemoteSource.getMovieVideo(movieId = id)
        assertTrue(result is ResultOf.Failure)
    }

}