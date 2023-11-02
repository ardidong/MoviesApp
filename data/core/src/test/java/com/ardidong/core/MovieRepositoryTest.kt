package com.ardidong.core

import com.ardidong.core.fake.FakeMovieData
import com.ardidong.core.movie.MovieRemoteSource
import com.ardidong.core.movie.MovieRepositoryImpl
import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.movie.MovieRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    @MockK
    private lateinit var remoteSource: MovieRemoteSource
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup(){
        remoteSource = mockk()
        movieRepository = MovieRepositoryImpl(remoteSource)
    }

    @Test
    fun getMovieByGenre_success_returnResultOfSuccessPagination() = runBlocking{
        val response = FakeMovieData.createDiscoverMovieResponse()
        coEvery { remoteSource.getMoviesByGenres(any(), any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.getMoviesByGenre(listOf(), 1)
        assertTrue(result is ResultOf.Success)
        assertEquals(response.results?.size ,(result as ResultOf.Success).data.results.size)
        assertEquals(response.results?.map { it?.title }, result.data.results.map { it.title } )
    }

    @Test
    fun getMovieByGenre_failed_returnResultOfFailure() = runBlocking{
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.getMoviesByGenres(any(), any()) } coAnswers {
            ResultOf.Failure(
                ErrorEntity.ApiResponseError(
                    errorMessage,
                    errorCode
                )
            )
        }

        val result = movieRepository.getMoviesByGenre(listOf(), 1)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }

    @Test
    fun searchMovie_withTitleQuery_returnResultOfSuccessPaginationWithGivenTitle() = runBlocking {
        val movieTitle = "Expend4bles"
        val response = FakeMovieData.createDiscoverMovieResponse(isResultEmpty = true).copy(
            results = listOf( FakeMovieData.createMovieItems().find { it.title == movieTitle })
        )
        coEvery { remoteSource.searchMovie(any(), any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.searchMovie(query = movieTitle, genres = emptyList(), page = 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(response.results?.first()?.title, (result as ResultOf.Success).data.results.first().title)
    }

    @Test
    fun searchMovie_withTitleQueryAndGenreId_returnResultOfSuccessPaginationWithGivenTitleAndGenre() = runBlocking {
        val movieTitle = "Mission"
        val genreId = 12
        val response = FakeMovieData.createDiscoverMovieResponse(isResultEmpty = true).copy(
            results = FakeMovieData.createMovieItems().filter { it.title?.contains(movieTitle) ?: false }
        )
        val movieCount = response.results?.count { it?.genreIds?.contains(genreId) ?: false }
        coEvery { remoteSource.searchMovie(any(), any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.searchMovie(query = movieTitle, genres = listOf(genreId), page = 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(movieCount, (result as ResultOf.Success).data.results.size)
        assertTrue(result.data.results.all { it.title.contains(movieTitle, true) })
        assertTrue(result.data.results.all { it.genreIds.contains(genreId) })
    }

    @Test
    fun searchMovie_withTitleQueryAndWrongGenreId_returnResultOfSuccessPaginationWithoutGivenTitle() = runBlocking {
        val movieTitle = "Expend4bles"
        val genre = 27
        val response = FakeMovieData.createDiscoverMovieResponse(isResultEmpty = true).copy(
            results = listOf( FakeMovieData.createMovieItems().find { it.title == movieTitle })
        )
        coEvery { remoteSource.searchMovie(any(), any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.searchMovie(query = movieTitle, genres = listOf(genre), page = 1)

        assertTrue(result is ResultOf.Success)
        assertTrue(!(result as ResultOf.Success).data.results.any{ it.title == movieTitle })
    }

    @Test
    fun searchMovie_failed_returnResultOfFailure() = runBlocking {
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.searchMovie(any(), any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))
        }

        val result = movieRepository.searchMovie(query = "", genres = listOf(), page = 1)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }

    @Test
    fun getMovieReview_success_returnResultOfSuccessListOfReview() = runBlocking {
        val movieId = 976573
        val response = FakeMovieData.createMovieReviewResponse()
        coEvery { remoteSource.getMovieReview(any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.getMovieReview(movieId, 1)

        assertTrue(result is ResultOf.Success)
        assertEquals(response.results?.first()?.id, (result as ResultOf.Success).data.results.first().id)
    }

    @Test
    fun getMovieReview_failed_returnResultOfFailure() = runBlocking {
        val movieId = 976573
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.getMovieReview(any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))
        }

        val result = movieRepository.getMovieReview(movieId, 1)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }

    @Test
    fun getMovieDetail_success_returnResultOfSuccessMovieDetail() = runBlocking {
        val movieId = 976573
        val response = FakeMovieData.createMovieDetailResponse()
        coEvery { remoteSource.getMovieDetail(any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.getMovieDetail(movieId)
        assertTrue(result is ResultOf.Success)
        assertEquals(response.title, (result as ResultOf.Success).data.title)
    }

    @Test
    fun getMovieDetail_failed_returnResultOfFailure() = runBlocking {
        val movieId = 976573
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.getMovieDetail(any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))
        }

        val result = movieRepository.getMovieDetail(movieId)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }

    @Test
    fun getMovieVideo_success_returnResultOfSuccessListOfVideo() = runBlocking {
        val movieId = 976573
        val response = FakeMovieData.createMovieVideoResponse()
        val listId = response.results?.map {
            it?.id!!
        }!!
        coEvery { remoteSource.getMovieVideo(any()) } coAnswers {
            ResultOf.Success(response)
        }

        val result = movieRepository.getMovieVideo(movieId)
        assertTrue(result is ResultOf.Success)
        assertEquals(response.results?.size, (result as ResultOf.Success).data.size)
        assertTrue(result.data.all { listId.contains(it.id) })
    }

    @Test
    fun getMovieVideo_failed_returnResultOfFailure() = runBlocking {
        val movieId = 976573
        val errorMessage = "internal server error"
        val errorCode = "500"
        coEvery { remoteSource.getMovieVideo(any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError(errorMessage, errorCode))
        }

        val result = movieRepository.getMovieVideo(movieId)

        assertTrue(result is ResultOf.Failure)
        assertEquals(errorMessage, (result as ResultOf.Failure).error.message)
    }
}