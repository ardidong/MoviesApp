package com.ardidong.domain.genre

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.genre.model.Genre

interface GetGenreListUseCase {
    suspend operator fun invoke(): ResultOf<List<Genre>>
}