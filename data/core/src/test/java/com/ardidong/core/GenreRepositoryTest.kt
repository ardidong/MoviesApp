package com.ardidong.core

import com.ardidong.core.fake.GenreDummyData
import com.ardidong.core.genre.GenreRemoteDataSource
import com.ardidong.core.genre.GenreRepositoryImpl
import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.genre.GenreRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GenreRepositoryTest {

    @MockK
    private lateinit var remoteSource: GenreRemoteDataSource
    private lateinit var genreRepository: GenreRepository

    @Before
    fun setup(){
        remoteSource = mockk()
        genreRepository = GenreRepositoryImpl(remoteSource)
    }

    @Test
    fun getGenreList_success_returnResultOfSuccessGenreList() = runBlocking {
        val response = GenreDummyData.getGenreList()
        val listId = response.genres?.map { it?.id!! }!!
        coEvery { remoteSource.getGenreList()  } coAnswers {
            ResultOf.Success(response)
        }

        val result = genreRepository.getGenreList()

        assertTrue(result is ResultOf.Success)
        assertEquals(response.genres?.size, (result as ResultOf.Success).data.size)
        assertTrue(result.data.all { listId.contains(it.id) })
    }

    @Test
    fun getGenreList_failure_returnResultOfFailure() = runBlocking{
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.getGenreList() } coAnswers {
            ResultOf.Failure(
                ErrorEntity.ApiResponseError(message = errorMessage, errorCode = errorCode)
            )
        }

        val result = genreRepository.getGenreList()

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }
}