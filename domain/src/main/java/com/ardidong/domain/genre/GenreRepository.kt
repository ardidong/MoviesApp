package com.ardidong.domain.genre

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.genre.model.Genre

interface GenreRepository {
    suspend fun getGenreList(): ResultOf<List<Genre>>
}