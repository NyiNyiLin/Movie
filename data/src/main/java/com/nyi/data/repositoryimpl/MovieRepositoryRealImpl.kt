package com.nyi.data.repositoryimpl

import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import com.nyi.domainn.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject


class MovieRepositoryRealImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource)
    : MovieRepository {

    override fun getTrendingMovie(movieType: String, time: String): Single<List<Movie>> {

        return Single.fromCallable{
            movieNetworkDataSource.getTrendingMovie(movieType, time)
        }

    }
}