package com.ardidong.core.movie.mapper

import com.ardidong.core.movie.model.MovieDetailResponse
import com.ardidong.domain.common.extension.orMin
import com.ardidong.domain.common.mapper.ResponseToModel
import com.ardidong.domain.genre.model.Genre
import com.ardidong.domain.movie.model.MovieDetail

class MovieDetailMapper : ResponseToModel<MovieDetailResponse, MovieDetail> {
    override fun toModel(response: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            originalLanguage = response.originalLanguage.orEmpty(),
            imdbId = response.imdbId.orEmpty(),
            video = response.video ?: false,
            title = response.title.orEmpty(),
            backdropPath = response.backdropPath.orEmpty(),
            revenue = response.revenue.orMin(),
            genres = response.genres?.map { genreItem ->
                Genre(
                    name = genreItem?.name.orEmpty(),
                    id = genreItem?.id.orMin()
                )
            } ?: emptyList(),
            popularity = response.popularity.orMin(),
            id = response.id.orMin(),
            voteCount = response.voteCount.orMin(),
            budget = response.budget.orMin(),
            overview = response.overview.orEmpty(),
            originalTitle = response.originalTitle.orEmpty(),
            runtime = response.runtime.orMin(),
            posterPath = response.posterPath.orEmpty(),
            productionCompanies = response.productionCompanies?.map { productionCompanyItem ->
                MovieDetail.ProductionCompany(
                    logoPath = productionCompanyItem?.logoPath.orEmpty(),
                    name = productionCompanyItem?.name.orEmpty(),
                    id = productionCompanyItem?.id.orMin(),
                    originCountry = productionCompanyItem?.originCountry.orEmpty()
                )
            } ?: emptyList(),
            releaseDate = response.releaseDate.orEmpty(),
            voteAverage = response.voteAverage.orMin(),
            tagline = response.tagline.orEmpty(),
            adult = response.adult ?: false,
            homepage = response.homepage.orEmpty(),
            status = response.status.orEmpty()
        )


    }
}