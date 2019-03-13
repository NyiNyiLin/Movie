package com.nyi.network.datasource.movie

import android.util.Log
import android.widget.Toast
import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import com.nyi.network.entity.MovieListResponse
import com.nyi.network.mapper.TrendingMapper
import com.nyi.network.service.MovieService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.security.auth.callback.Callback

class movieDataSourceRealImpl @Inject constructor(
    private val movieService : MovieService,
    private val trendingMapper: TrendingMapper
) : MovieNetworkDataSource {

    override fun getTrendingMovie(movieType: String, time: String): List<Movie> {

        /*movieService.getTrendingMovie().enqueue(object : retrofit2.Callback<MovieListResponse>{
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {

                Log.d("aa", "success")
                Log.d("aa", response.body()?.page.toString())
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {

                Log.d("aa", "fail" + t.message)
            }
        })*/

        //Log.d("aa", movieService.getTrendingMovie().execute().body().toString())

        return trendingMapper.map(movieService.getTrendingMovie().execute().body()!!)

        /*var dummyMovieList = ArrayList<Movie>()
        dummyMovieList.add(Movie(MovieId(3), "Harry Potter and The Prinsoner of Azakarban", "Harry Potter 1"))
        dummyMovieList.add(Movie(MovieId(3), "Harry Potter and The Prinsoner of Azakarban", "Harry Potter 1"))

        return dummyMovieList*/

    }
}