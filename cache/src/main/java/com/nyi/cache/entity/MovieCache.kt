package com.nyi.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "movie")
data class MovieCache(
    @Json(name = "movie_id")
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    var movieId: Int,

    @ColumnInfo(name = "name")
    @Json(name = "name")
    var name : String,

    @ColumnInfo(name = "description")
    @Json(name = "description")
    var description : String,

    @ColumnInfo(name = "backdrop")
    @Json(name = "backdrop")
    var backdrop : String
)