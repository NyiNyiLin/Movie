package com.nyi.domainn.interactor.movie

import com.nyi.domainn.model.Movie
import com.nyi.domainn.repository.MovieRepository
import javax.inject.Inject

class GetTrending @Inject constructor(
    private val movieRespository: MovieRepository
) {

    suspend fun getTrendingMovie(params: GetTrending.Params) : List<Movie>{
        return movieRespository.getTrendingMovie(params.mediaType, params.time)
    }

    suspend fun getLocalTrendingMovie(params: GetTrending.Params) : List<Movie>{
        return movieRespository.getLocalTrendingMovie()
    }



    /*override fun provideSingledee(params: GetTrending.Params): Single<List<Movie>> {
        return movieRespository.getTrendingMovie(params.mediaType, params.time)
    }*/

    data class Params(
        val mediaType : String,
        val time : String
    )
}