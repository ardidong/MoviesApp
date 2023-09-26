package com.ardidong.core.movie.mapper

import com.ardidong.core.movie.model.MovieVideoResponse
import com.ardidong.domain.common.extension.orMin
import com.ardidong.domain.common.mapper.ResponseToModel
import com.ardidong.domain.movie.model.MovieVideo

class MovieVideoMapper : ResponseToModel<MovieVideoResponse, List<MovieVideo>> {
    override fun toModel(response: MovieVideoResponse): List<MovieVideo> {
        return response.results?.map { item ->
            MovieVideo(
                site = item?.site.orEmpty(),
                size = item?.size.orMin(),
                iso31661 = item?.iso31661.orEmpty(),
                name = item?.name.orEmpty(),
                official = item?.official ?: false,
                id = item?.id.orEmpty(),
                type = item?.type.orEmpty(),
                publishedAt = item?.publishedAt.orEmpty(),
                iso6391 = item?.iso6391.orEmpty(),
                key = item?.key.orEmpty()
            )
        }.orEmpty()
    }
}
