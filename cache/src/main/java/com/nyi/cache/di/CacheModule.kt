package com.nyi.cache.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.nyi.cache.datasource.MovieCacheDatasourceRealImpl
import com.nyi.data.datasource.MovieCacheDataSource
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

  }

}