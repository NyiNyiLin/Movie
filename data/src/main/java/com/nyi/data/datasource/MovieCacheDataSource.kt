package com.nyi.data.datasource

import android.arch.paging.DataSource
import com.nyi.domainn.model.Movie

interface MovieCacheDataSource {

    suspend fun getMovie() : DataSource<Int, List<Movie>>

    suspend fun putMovie(movieList : List<Movie>)
}