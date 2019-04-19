package com.nyi.cache.mapper

import com.nyi.cache.entity.MovieCache
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import javax.inject.Inject


class MovieCacheEntityMapper @Inject constructor(){

    fun map(movie : Movie) : MovieCache{

        return MovieCache(
            movieId = movie.movieId.value,
            name = movie.name,
            description = movie.description
        )
    }

    fun map(list : List<MovieCache>) : ArrayList<Movie>{
        var convertedList = ArrayList<Movie>()

        for(movie in list){
            convertedList.add(
                Movie(
                    movieId = MovieId(movie.movieId),
                    name = movie.name,
                    description = movie.description
                )
            )
        }

        return convertedList
    }

}