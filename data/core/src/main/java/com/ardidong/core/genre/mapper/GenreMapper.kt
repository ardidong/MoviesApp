package com.ardidong.core.genre.mapper

import com.ardidong.core.genre.model.GenreListResponse
import com.ardidong.domain.common.extension.orMin
import com.ardidong.domain.common.mapper.ResponseToModel
import com.ardidong.domain.genre.model.Genre

class GenreMapper :
    ResponseToModel<GenreListResponse, List<Genre>>
{
    override fun toModel(response: GenreListResponse): List<Genre> {
        return response.genres?.map {
            Genre(
                id = it?.id.orMin(),
                name = it?.name.orEmpty(),
            )
        }.orEmpty()
    }
}