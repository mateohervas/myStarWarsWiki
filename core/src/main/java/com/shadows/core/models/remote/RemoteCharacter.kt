package com.shadows.core.models.remote

import com.google.gson.annotations.SerializedName

data class RemoteCharacter(
	val films: List<String>?,
	@SerializedName("homeworld")
	val homeWorld: String?,
	val url: String?,
	@SerializedName("birth_year")
	val birthYear: String?,
	val species: List<String>?,
	val name: String?,
	val height: String?
)

