package com.nyi.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieItem>
)