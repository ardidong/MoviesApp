package com.ardidong.domain.dummy

import com.ardidong.domain.genre.model.Genre

object GenreDummyData {
    fun getGenreList() = listOf(
            Genre("Action", 28),
            Genre("Adventure", 12),
            Genre("Animation", 16),
            Genre("Comedy", 35),
            Genre("Crime", 80),
            Genre("Documentary", 99),
            Genre("Drama", 18),
            Genre("Family", 10751),
            Genre("Fantasy", 14),
            Genre("History", 36),
            Genre("Horror", 27),
            Genre("Music", 10402),
            Genre("Mystery", 9648),
            Genre("Romance", 10749),
            Genre("Science Fiction", 878),
            Genre("TV Movie", 10770),
            Genre("Thriller", 53),
            Genre("War", 10752),
            Genre("Western", 37)
        )

}