package com.nyi.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieItem(
    @Json(name = "id") val id: Int,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "title") val title: String,
    @Json(name = "popularity") val popularity: Double
)