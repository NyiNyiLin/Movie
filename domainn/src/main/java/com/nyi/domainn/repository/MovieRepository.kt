package com.nyi.domainn.repository

import com.nyi.domainn.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getTrendingMovie(
        movieType : String,
        time : String
    ) : Single<List<Movie>>
}