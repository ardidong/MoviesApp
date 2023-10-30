package com.ardidong.moviesapp.ui.home

import androidx.paging.PagingData
import com.ardidong.domain.genre.model.Genre
import com.ardidong.domain.movie.model.Movie
import com.ardidong.moviesapp.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeScreenState(
    val genreState: UiState<List<Genre>>,
    val movies: Flow<PagingData<Movie>>,
    val selectedGenre: Set<Int>,
    val titleFilter: String
){
    companion object {
        val EMPTY = HomeScreenState(
            genreState = UiState.Loading,
            movies = emptyFlow(),
            selectedGenre = setOf(),
            titleFilter = ""
        )
    }
}
