package com.nyi.cache.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.nyi.cache.Database
import com.nyi.cache.datasource.MovieCacheDatasourceRealImpl
import com.nyi.data.datasource.MovieCacheDataSource
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [CacheModule.Providers::class])
abstract class CacheModule {

    @Binds
    abstract fun movieCacheDataSource(movieCacheDatasourceRealImpl: MovieCacheDatasourceRealImpl): MovieCacheDataSource

    @Module
    internal object Providers {

        @JvmStatic
        @Provides
        fun sharedPref(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun sqlDriver(context: Context): SqlDriver {
            return AndroidSqliteDriver(Database.Schema, context, "movie.db")
        }

        @Provides
        @Singleton
        @JvmStatic
        fun database(sqlDriver: SqlDriver): Database {

//      val requestIdAdapter = object : ColumnAdapter<PickupRequestId, Long> {
//        override fun decode(databaseValue: Long): PickupRequestId {
//          return PickupRequestId(databaseValue)
//        }
//
//        override fun encode(value: PickupRequestId): Long {
//          return value.value
//        }
//
//      }
//
//      val wasteIdAdapter = object : ColumnAdapter<RecycleWasteId, Long> {
//        override fun decode(databaseValue: Long): RecycleWasteId {
//          return RecycleWasteId(databaseValue)
//        }
//
//        override fun encode(value: RecycleWasteId): Long {
//          return value.value
//        }
//
//      }

            return Database(
                sqlDriver
            )
        }

    }

}