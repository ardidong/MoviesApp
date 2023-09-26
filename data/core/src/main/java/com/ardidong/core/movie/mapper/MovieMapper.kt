package com.ardidong.core.movie.mapper

import com.ardidong.core.movie.model.DiscoverMovieResponse
import com.ardidong.domain.common.extension.orMin
import com.ardidong.domain.common.extension.orZero
import com.ardidong.domain.common.mapper.ResponseToModel
import com.ardidong.domain.movie.model.Movie
import com.ardidong.domain.movie.model.PagedData

class MovieMapper : ResponseToModel<DiscoverMovieResponse, PagedData<Movie>> {
    override fun toModel(response: DiscoverMovieResponse): PagedData<Movie> {
        return PagedData(
            page = response.page.orZero(),
            totalPages = response.totalPages.orZero(),
            totalResults = response.totalResults.orZero(),
            results = response.results?.map { item ->
                Movie(
                    overview = item?.overview.orEmpty(),
                    originalLanguage = item?.originalLanguage.orEmpty(),
                    originalTitle = item?.originalTitle.orEmpty(),
                    video = item?.video ?: false,
                    title = item?.title.orEmpty(),
                    genreIds = item?.genreIds?.mapNotNull { it } ?: emptyList(),
                    posterPath = item?.posterPath.orEmpty(),
                    backdropPath = item?.backdropPath.orEmpty(),
                    releaseDate = item?.releaseDate.orEmpty(),
                    popularity = item?.popularity.orMin(),
                    voteAverage = item?.voteAverage.orMin(),
                    id = item?.id.orMin(),
                    adult = item?.adult ?: false,
                    voteCount = item?.voteCount ?: 0
                )
            }.orEmpty()
        )
    }

}