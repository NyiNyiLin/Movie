package com.nyi.network.service

import com.nyi.network.entity.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface MovieService {

    @GET("trending/movie/day?api_key=b0162e9de9b7dbdcf03f01443c6fce34")
    fun getTrendingMovie(
    ): Deferred<MovieListResponse>


}