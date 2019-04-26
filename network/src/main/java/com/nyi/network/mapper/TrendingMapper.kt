package com.nyi.network.mapper

import com.nyi.domainn.mapper.UnidirectionalMap
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import com.nyi.network.entity.MovieListResponse
import javax.inject.Inject

/**
 * Created by Vincent on 2/23/19
 */

class TrendingMapper @Inject constructor() :
  UnidirectionalMap<MovieListResponse, List<Movie>> {

  override fun map(item: MovieListResponse): List<Movie> {
    var dummyMovieList = ArrayList<Movie>()

    for(item in item.results){
      var newMovie = Movie()
      newMovie.movieId = MovieId(item.id)
      newMovie.name = item.title
      newMovie.description = item.popularity.toString()
      newMovie.backdrop = item.backdropPath

      dummyMovieList.add(newMovie)
    }

    return dummyMovieList
  }

}