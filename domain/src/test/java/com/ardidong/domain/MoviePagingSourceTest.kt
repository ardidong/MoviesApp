package com.ardidong.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.dummy.MoviesDummyData
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.paging.MoviePagingSource
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MoviePagingSourceTest {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var pagingSource: MoviePagingSource

    @Before
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun load_getMovieWithoutQueryAndGenre_returnPagingSourceLoadResultPage() = runTest {
        val genre = listOf<Int>()
        val query = ""

        val expectedData = MoviesDummyData.createDiscoverMovieData()
        coEvery { repository.getMoviesByGenre(any(), any()) } coAnswers {
            ResultOf.Success(expectedData)
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )


        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Page)
        (result as PagingSource.LoadResult.Page)
        assertEquals(expectedData.results, result.data)
        assertEquals(null, result.nextKey)
        coVerify { repository.getMoviesByGenre(genres = genre, page = 1) }
    }

    @Test
    fun load_getMovieWithoutQueryAndGenreWith2Pages_returnPagingSourceLoadResultPage() = runTest {
        val genre = listOf<Int>()
        val query = ""
        val expectedData = MoviesDummyData.createDiscoverMovieData().copy(
            totalPages = 2
        )

        coEvery { repository.getMoviesByGenre(any(), any()) } coAnswers {
            ResultOf.Success(expectedData)
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Page)
        (result as PagingSource.LoadResult.Page)
        assertEquals(expectedData.results, result.data)
        assertEquals(2, result.nextKey)
        coVerify { repository.getMoviesByGenre(genres = genre, page = 1) }
    }

    @Test
    fun load_getMovieWithoutQueryAndGenreFailed_returnPagingSourceLoadResultError() = runTest {
        val genre = listOf<Int>()
        val query = ""

        coEvery { repository.getMoviesByGenre(any(), any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError("",""))
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun load_getMovieWithoutQueryAndGenreException_returnPagingSourceLoadResultError() = runTest {
        val genre = listOf<Int>()
        val query = ""

        coEvery { repository.getMoviesByGenre(any(), any()) }.throws(RuntimeException())

        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh(

        )
        assertTrue(result is PagingSource.LoadResult.Error)
    }


    @Test
    fun load_getMovieWithQueryAndGenre_returnPagingSourceLoadResultPage() = runTest {
        val genre = listOf<Int>()
        val query = "Mission"
        val expectedData = MoviesDummyData.createDiscoverMovieData().copy(
            results = MoviesDummyData.createMovieList().filter { it.title.contains(query) }
        )

        coEvery { repository.searchMovie(any(), any(), any()) } coAnswers {
            ResultOf.Success(expectedData)
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Page)
        (result as PagingSource.LoadResult.Page)
        assertEquals(expectedData.results, result.data)
        assertEquals(null, result.nextKey)
        coVerify { repository.searchMovie(genres = genre, query = query, page = 1) }
    }

    @Test
    fun load_getMovieWithQueryAndGenre2Pages_returnPagingSourceLoadResultPage() = runTest {
        val genre = listOf<Int>()
        val query = "Mission"
        val expectedData = MoviesDummyData.createDiscoverMovieData().copy(
            results = MoviesDummyData.createMovieList().filter { it.title.contains(query) },
            totalPages = 2
        )

        coEvery { repository.searchMovie(any(), any(), any()) } coAnswers {
            ResultOf.Success(expectedData)
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Page)
        (result as PagingSource.LoadResult.Page)
        assertEquals(expectedData.results, result.data)
        assertEquals(2, result.nextKey)
        coVerify { repository.searchMovie(genres = genre, query = query, page = 1) }
    }

    @Test
    fun load_getMovieWithQueryAndGenreFailed_returnPagingSourceLoadResultError() = runTest {
        val genre = listOf<Int>()
        val query = "Mission"
        coEvery { repository.searchMovie(any(), any(), any()) } coAnswers {
            ResultOf.Failure(ErrorEntity.ApiResponseError("",""))
        }
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun load_getMovieWithQueryAndGenreException_returnPagingSourceLoadResultError() = runTest {
        val genre = listOf<Int>()
        val query = "Mission"

        coEvery { repository.searchMovie(any(), any(), any()) }.throws(RuntimeException())
        pagingSource = MoviePagingSource(repository, query, genre)
        val pager = TestPager(
            config = PagingConfig(
                20
            ),
            pagingSource = pagingSource
        )

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Error)
    }
}