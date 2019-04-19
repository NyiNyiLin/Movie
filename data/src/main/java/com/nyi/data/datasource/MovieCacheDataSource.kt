package com.nyi.data.datasource

import com.nyi.domainn.model.Movie

interface MovieCacheDataSource {

    suspend fun getMovie() : ArrayList<Movie>

    suspend fun putMovie(movieList : ArrayList<Movie>)
}