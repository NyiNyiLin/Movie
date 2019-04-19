package com.nyi.data.repositoryimpl

import com.nyi.data.datasource.MovieCacheDataSource
import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import com.nyi.domainn.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject


class MovieRepositoryRealImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
)
    : MovieRepository {

     /*fun getTrendingMovieee(movieType: String, time: String): Single<List<Movie>> {

        return Single.fromCallable{
            movieNetworkDataSource.getTrendingMovie(movieType, time)
        }

    }*/

    override suspend fun getTrendingMovie(movieType: String, time: String): List<Movie> {


        try{
            val networkMovieList = movieNetworkDataSource.getTrendingMovie(movieType, time)
            movieCacheDataSource.putMovie(networkMovieList)
        }catch (t : Throwable){

        }

        val cacheMovieList = movieCacheDataSource.getMovie()
        return cacheMovieList
    }

    override suspend fun getLocalTrendingMovie(): List<Movie> {
        val cacheMovieList = movieCacheDataSource.getMovie()
        return cacheMovieList
    }
}