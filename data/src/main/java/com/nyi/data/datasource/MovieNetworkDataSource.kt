package com.nyi.data.datasource

import com.nyi.data.entity.MovieEntity
import com.nyi.domainn.model.Movie

interface MovieNetworkDataSource {

    suspend fun getTrendingMovie(movieType :String, time : String) : List<Movie>
}