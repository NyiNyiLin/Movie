package com.nyi.cache.daos

import androidx.paging.DataSource
import androidx.room.*
import com.nyi.cache.entity.MovieCache
import com.nyi.domainn.model.Movie


@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    suspend fun getAllMovie(): DataSource<Int, List<MovieCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie : MovieCache)

    @Delete
    suspend fun delete(movie: MovieCache)

    @Query("DELETE FROM movie")
    suspend fun deleteMovieTable()
}