package com.ardidong.domain.movie.model

import com.ardidong.domain.genre.model.Genre

data class MovieDetail(
	val originalLanguage: String,
	val imdbId: String,
	val video: Boolean,
	val title: String,
	val backdropPath: String,
	val revenue: Int,
	val genres: List<Genre>,
	val popularity: Double,
	val id: Int,
	val voteCount: Int,
	val budget: Int,
	val overview: String,
	val originalTitle: String,
	val runtime: Int,
	val posterPath: String,
	val productionCompanies: List<ProductionCompany>,
	val releaseDate: String,
	val voteAverage: Double,
	val tagline: String,
	val adult: Boolean,
	val homepage: String,
	val status: String
){
	data class ProductionCompany(
		val logoPath: String,
		val name: String,
		val id: Int,
		val originCountry: String
	)
}
