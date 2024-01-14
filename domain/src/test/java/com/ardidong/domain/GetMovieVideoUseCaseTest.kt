package com.ardidong.domain

import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.dummy.MoviesDummyData
import com.ardidong.domain.movie.GetMovieVideoUseCase
import com.ardidong.domain.movie.GetMovieVideoUseCaseImpl
import com.ardidong.domain.movie.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMovieVideoUseCaseTest {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieVideoUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        useCase = GetMovieVideoUseCaseImpl(repository)
    }

    @Test
    fun getMovie_success_returnResultOfSuccessListMovieVideo() = runTest{
        val expectedData = MoviesDummyData.createMovieVideoList()
        val movieId = 976573

        coEvery { repository.getMovieVideo(any()) } returns ResultOf.Success(expectedData)

        val result = useCase(movieId)

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedData, (result as ResultOf.Success).data)
    }

    @Test
    fun getMovie_failed_returnResultOfFailure() = runTest {
        val errorMessage = "Internal server error"
        val errorCode = "500"
        val movieId = 976573

        coEvery { repository.getMovieVideo(any()) } returns
                ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))

        val result = useCase(movieId)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)

    }

}