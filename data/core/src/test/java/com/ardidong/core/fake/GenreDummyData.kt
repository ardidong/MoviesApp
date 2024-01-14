package com.ardidong.core.fake

import com.ardidong.core.genre.model.GenreListResponse

object GenreDummyData {
    fun getGenreList() = GenreListResponse(
        listOf(
            GenreListResponse.GenresItem("Action", 28),
            GenreListResponse.GenresItem("Adventure", 12),
            GenreListResponse.GenresItem("Animation", 16),
            GenreListResponse.GenresItem("Comedy", 35),
            GenreListResponse.GenresItem("Crime", 80),
            GenreListResponse.GenresItem("Documentary", 99),
            GenreListResponse.GenresItem("Drama", 18),
            GenreListResponse.GenresItem("Family", 10751),
            GenreListResponse.GenresItem("Fantasy", 14),
            GenreListResponse.GenresItem("History", 36),
            GenreListResponse.GenresItem("Horror", 27),
            GenreListResponse.GenresItem("Music", 10402),
            GenreListResponse.GenresItem("Mystery", 9648),
            GenreListResponse.GenresItem("Romance", 10749),
            GenreListResponse.GenresItem("Science Fiction", 878),
            GenreListResponse.GenresItem("TV Movie", 10770),
            GenreListResponse.GenresItem("Thriller", 53),
            GenreListResponse.GenresItem("War", 10752),
            GenreListResponse.GenresItem("Western", 37)
        )
    )
}