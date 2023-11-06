package com.ardidong.domain

import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.dummy.MoviesDummyData
import com.ardidong.domain.movie.GetMovieDetailUseCase
import com.ardidong.domain.movie.GetMovieDetailUseCaseImpl
import com.ardidong.domain.movie.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMovieDetailUseCaseTest {

    @MockK
    private lateinit var movieRepository: MovieRepository
    private lateinit var usecase: GetMovieDetailUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        usecase = GetMovieDetailUseCaseImpl(repository = movieRepository)
    }

    @Test
    fun getMovieDetail_success_returnResultOfSuccessMovieDetail() = runTest{
        val expectedData = MoviesDummyData.createMovieDetail()

        coEvery { movieRepository.getMovieDetail(any()) } returns ResultOf.Success(expectedData)

        val result = usecase(expectedData.id)

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedData, (result as ResultOf.Success).data)
    }

    @Test
    fun getMovieDetail_failed_returnResultOfFailure() = runTest{
        val errorMessage = "Internal server error"
        val errorCode = "500"

        coEvery { movieRepository.getMovieDetail(any()) } returns
                ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))

        val result = usecase(976573)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }

}