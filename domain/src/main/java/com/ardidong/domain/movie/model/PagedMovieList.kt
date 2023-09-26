package com.ardidong.domain.movie.model

data class PagedData<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int,
)
