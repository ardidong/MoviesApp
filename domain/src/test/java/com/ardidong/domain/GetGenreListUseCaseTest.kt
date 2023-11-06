package com.ardidong.domain

import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.dummy.GenreDummyData
import com.ardidong.domain.genre.GenreRepository
import com.ardidong.domain.genre.GetGenreListUseCase
import com.ardidong.domain.genre.GetGenreListUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetGenreListUseCaseTest {
    @MockK
    private lateinit var repository: GenreRepository
    private lateinit var useCase: GetGenreListUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        useCase = GetGenreListUseCaseImpl(repository)
    }

    @Test
    fun getGenreList_success_returnResultOfSuccessListGenre() = runTest {
        val expectedData = GenreDummyData.getGenreList()

        coEvery { repository.getGenreList() } returns ResultOf.Success(expectedData)

        val result = useCase()

        assertTrue(result is ResultOf.Success)
        assertEquals(expectedData, (result as ResultOf.Success).data)
    }

    @Test
    fun getGenreList_failed_returnResultOfFailure() = runTest {
        val errorMessage = "Internal server error"
        val errorCode = "500"

        coEvery { repository.getGenreList() } returns
                ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))


        val result = useCase()

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }
}