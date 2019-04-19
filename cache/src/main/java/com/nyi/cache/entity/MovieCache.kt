package com.nyi.cache.entity

import com.nyi.domainn.model.MovieId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCache(
    @Json(name = "movie_id") var movieId: MovieId,
    @Json(name = "name") var name : String,
    @Json(name = "description") var description : String
)