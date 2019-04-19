package com.nyi.cache.datasource

import android.content.SharedPreferences
import com.nyi.data.datasource.MovieCacheDataSource
import com.nyi.domainn.model.Movie
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MovieCacheDatasourceRealImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
    ): MovieCacheDataSource {

    companion object {
        private const val PREF_KEY_MOVIE = "movie"
    }

    private val moshi = Moshi.Builder().build()

    override suspend fun getMovie(): ArrayList<Movie> {
        var arrayList = ArrayList<Movie>()
        arrayList.add(Movie())
        return arrayList
    }

    override suspend fun putMovie(movieList: ArrayList<Movie>) {

    }
}