package com.ardidong.core.movie.mapper

import com.ardidong.core.movie.model.MovieReviewResponse
import com.ardidong.domain.common.extension.orMin
import com.ardidong.domain.common.extension.orZero
import com.ardidong.domain.common.mapper.ResponseToModel
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.domain.movie.model.PagedData
import com.ardidong.domain.movie.model.ReviewAuthor

class MovieReviewMapper : ResponseToModel<MovieReviewResponse, PagedData<MovieReview>> {
    override fun toModel(response: MovieReviewResponse): PagedData<MovieReview> {
        return PagedData(
            page = response.page.orZero(),
            totalPages = response.totalPages.orZero(),
            totalResults = response.totalResults.orZero(),
            results = response.results?.map { resultItem ->
                val authorDetails = resultItem?.authorDetails
                MovieReview(
                    authorDetails = ReviewAuthor(
                        avatarPath = authorDetails?.avatarPath.orEmpty(),
                        name = authorDetails?.name.orEmpty(),
                        rating = authorDetails?.rating.orMin(),
                        username = authorDetails?.username.orEmpty()
                    ),
                    updatedAt = resultItem?.updatedAt.orEmpty(),
                    author = resultItem?.author.orEmpty(),
                    createdAt = resultItem?.createdAt.orEmpty(),
                    id = resultItem?.id.orEmpty(),
                    content = resultItem?.content.orEmpty(),
                    url = resultItem?.url.orEmpty()
                )
            }.orEmpty()
        )
    }
}