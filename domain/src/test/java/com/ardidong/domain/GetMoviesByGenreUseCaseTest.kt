package com.ardidong.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.dummy.MoviesDummyData
import com.ardidong.domain.movie.MovieRepository
import com.ardidong.domain.movie.list.GetMoviesByGenreUseCase
import com.ardidong.domain.movie.list.GetMoviesByGenreUseCaseImpl
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.model.PagedData
import com.ardidong.domain.movie.paging.MoviePagingSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMoviesByGenreUseCaseTest {

    @MockK
    private lateinit var repository: MovieRepository

    private lateinit var usecase: GetMoviesByGenreUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        repository = mockk()
        usecase = GetMoviesByGenreUseCaseImpl(repository)
    }
//
//    @Test
//    fun getMoviesByGenre_success_return() = runTest {
//        val responseData = MoviesDummyData.createDiscoverMovieData().results
//        val pagingSource = mockk<MoviePagingSource>(relaxed = true)
//        val mockFlow: Flow<PagingData<Movie>> = flowOf(PagingData.from(responseData))
//        val mockPager = mockk<Pager<Int, Movie>>()
//
//        coEvery {
//            Pager(
//                config = any(),
//                initialKey = any(),
//                pagingSourceFactory = any<() -> PagingSource<Int, Movie>>()
//            ).flow
//        } answers { mockPager.flow }
//
//        coEvery { mockPager.flow } returns mockFlow
//
//
//        val result : Flow<PagingData<Movie>> = usecase(listOf())
//        result.collect{
//            println(it)
//        }
//    }
}