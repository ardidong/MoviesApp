package com.ardidong.moviesapp.ui.detail

import com.ardidong.domain.movie.model.MovieDetail
import com.ardidong.domain.movie.model.MovieVideo
import com.ardidong.moviesapp.ui.UiState

data class DetailScreenState(
    val detail: UiState<MovieDetail>,
    val video: UiState<MovieVideo>,
){
    companion object{
        fun getInitial() = DetailScreenState(
            detail = UiState.Loading,
            video =  UiState.Loading
        )
    }
}
