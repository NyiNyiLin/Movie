package com.nyi.cache.datasource

import android.content.Context
import android.content.SharedPreferences
import com.nyi.cache.MovieDatabase
import com.nyi.cache.mapper.MovieCacheEntityMapper
import com.nyi.data.datasource.MovieCacheDataSource
import com.nyi.domainn.model.Movie
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MovieCacheDatasourceRealImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val mapper : MovieCacheEntityMapper,
    private val context: Context
    ): MovieCacheDataSource {

    companion object {
        private const val PREF_KEY_MOVIE = "movie"
    }

    private val moshi = Moshi.Builder().build()

    override suspend fun getMovie(): ArrayList<Movie> {
        val movieList = MovieDatabase.getDatabase(context).movieDao().getAllMovie()
        return mapper.map(movieList)
    }

    override suspend fun putMovie(movieList: List<Movie>) {
        for(movie in movieList){
            val convertedMovie = mapper.map(movie)
            MovieDatabase.getDatabase(context).movieDao().insert(convertedMovie)
        }


    }
}