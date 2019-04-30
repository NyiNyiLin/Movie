package com.nyi.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nyi.cache.daos.MovieDao
import com.nyi.cache.entity.MovieCache


@Database(entities = [MovieCache::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "myDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}