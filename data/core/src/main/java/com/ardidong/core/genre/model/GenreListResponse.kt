package com.ardidong.core.genre.model

import com.google.gson.annotations.SerializedName

data class GenreListResponse(

	@SerializedName("genres")
	val genres: List<GenresItem?>? = null
){
	data class GenresItem(

		@SerializedName("name")
		val name: String? = null,

		@SerializedName("id")
		val id: Int? = null
	)

}

