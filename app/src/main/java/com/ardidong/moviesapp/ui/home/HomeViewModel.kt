package com.ardidong.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ardidong.domain.genre.GetGenreListUseCase
import com.ardidong.domain.movie.GetMoviesByGenreUseCase
import com.ardidong.moviesapp.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchGetGenre : GetGenreListUseCase,
    private val dispatchGetMovies: GetMoviesByGenreUseCase
): ViewModel() {

    private var _homeState = MutableStateFlow(HomeScreenState.EMPTY)
    val homesState: StateFlow<HomeScreenState> get() = _homeState

    init {
        getGenreList()
        getMovieByGenre(listOf())
    }

    fun getGenreList(){
        updateState { it.copy(genreState = UiState.Loading) }
        viewModelScope.launch(Dispatchers.IO) {
            dispatchGetGenre().fold(
                success = { list ->
                    updateState { it.copy( genreState = UiState.Success(list) ) }
                },
                failure = { error ->
                    updateState { it.copy(genreState = UiState.Error(error)) }
                }
            )
        }
    }

    fun getMovieByGenre(ids: List<Int>){
        viewModelScope.launch(Dispatchers.IO){
            updateState {
                it.copy(
                    movies = emptyFlow()
                )
            }
            val data = dispatchGetMovies(ids).cachedIn(viewModelScope)
            updateState {
                it.copy(
                    movies = data
                )
            }
        }
    }

    fun addGenre(id: Int){
        updateState {
            val newSet = it.selectedGenre + id
            getMovieByGenre(newSet.toList())
            it.copy(selectedGenre = newSet )
        }
    }

    fun removeGenre(id: Int){
        updateState {
            val newSet = it.selectedGenre - id
            getMovieByGenre(newSet.toList())
            it.copy(selectedGenre = newSet )
        }
    }

    private fun updateState(update: (HomeScreenState) -> HomeScreenState) {
        _homeState.value = update(_homeState.value)
    }
}