package com.ardidong.domain.movie.model

data class MovieReview(
	val authorDetails: ReviewAuthor,
	val updatedAt: String,
	val author: String,
	val createdAt: String,
	val id: String,
	val content: String,
	val url: String
)

data class ReviewAuthor(
	val avatarPath: String,
	val name: String,
	val rating: Any,
	val username: String
)

