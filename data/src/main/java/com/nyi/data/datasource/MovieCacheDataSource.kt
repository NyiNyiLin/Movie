package com.nyi.data.datasource

import com.nyi.domainn.model.Movie

interface MovieCacheDataSource {

    suspend fun getMovie() : List<Movie>

    suspend fun putMovie(movieList : List<Movie>)
}