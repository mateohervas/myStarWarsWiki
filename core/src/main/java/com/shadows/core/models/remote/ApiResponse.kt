package com.shadows.core.models.remote

data class ApiResponse<T>(
	val next: String?,
	val previous: String?,
	val count: Int?,
	val results: List<T>?
)

