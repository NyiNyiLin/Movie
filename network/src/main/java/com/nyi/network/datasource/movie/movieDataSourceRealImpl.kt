package com.nyi.network.datasource.movie

import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import com.nyi.network.entity.MovieListResponse
import com.nyi.network.mapper.TrendingMapper
import com.nyi.network.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class movieDataSourceRealImpl @Inject constructor(
    private val movieService : MovieService,
    private val trendingMapper: TrendingMapper
) : MovieNetworkDataSource {

    override suspend fun getTrendingMovie(movieType: String, time: String): List<Movie> {


        /*return suspendCoroutine {

            movieService.getTrendingMovie().enqueue(object  : Callback<MovieListResponse> {
                override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                    val mappResult = trendingMapper.map(response.body()!!)
                    it.resume(mappResult)
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
            *//*val response = movieService.getTrendingMovie().execute()!!.body()


            val mappResult = trendingMapper.map(response!!)
            it.resume(mappResult)*//*
            //return trendingMapper.map(response)

        }*/

        val response = movieService.getTrendingMovie().await()
        return trendingMapper.map(response)
    }


}