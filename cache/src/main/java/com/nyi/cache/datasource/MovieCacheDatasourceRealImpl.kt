package com.nyi.cache.datasource

import android.content.Context
import android.content.SharedPreferences
import com.nyi.cache.Database
import com.nyi.cache.MovieRoomDatabase
import com.nyi.cache.mapper.MovieCacheEntityMapper
import com.nyi.data.datasource.MovieCacheDataSource
import com.nyi.domainn.model.Movie
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MovieCacheDatasourceRealImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val mapper : MovieCacheEntityMapper,
    private val context: Context,
    private val moviedatabase: Database
    ): MovieCacheDataSource {

    companion object {
        private const val PREF_KEY_MOVIE = "movie"
    }

    private val moshi = Moshi.Builder().build()

    override suspend fun getMovie(): ArrayList<Movie> {
        val movieList = MovieRoomDatabase.getDatabase(context).movieDao().getAllMovie()

        //return mapper.map(movieList)

        //sqdelight
        val movieListSqdelight = moviedatabase.movieDatabaseQueries.select_all().executeAsList()

        return mapper.mapp(movieListSqdelight)
    }

    override suspend fun putMovie(movieList: List<Movie>) {
        for(movie in movieList){
            val convertedMovie = mapper.map(movie)
            MovieRoomDatabase.getDatabase(context).movieDao().insert(convertedMovie)

            //this is sqdelight
            moviedatabase.movieDatabaseQueries.insert_or_replace(movieId = movie.movieId.value.toLong(),
                name = movie.name, description = movie.description, backdrop = movie.backdrop)
        }

    }
}