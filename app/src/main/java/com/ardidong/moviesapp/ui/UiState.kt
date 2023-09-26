package com.ardidong.moviesapp.ui

import com.ardidong.domain.common.ErrorEntity

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: ErrorEntity) : UiState<Nothing>()
}
