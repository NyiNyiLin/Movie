package com.nyi.network.datasource.movie

import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import javax.inject.Inject

class movieDataSourceFakeImpl@Inject constructor() : MovieNetworkDataSource {

    /*override fun getTrendingMovie(movieType: String, time: String): List<Movie> {
        var dummyMovieList = ArrayList<Movie>()
        dummyMovieList.add(Movie(MovieId(3), "Harry Potter and The Prinsoner of Azakarban", "Harry Potter 1"))
        dummyMovieList.add(Movie(MovieId(3), "Harry Potter and The Prinsoner of Azakarban", "Harry Potter 1"))

        return dummyMovieList
    }*/

    override suspend fun getTrendingMovie(movieType: String, time: String): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}