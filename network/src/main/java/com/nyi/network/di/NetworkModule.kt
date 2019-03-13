package com.nyi.network.di

import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.network.BuildConfig
import com.nyi.network.auth.AuthTokenInterceptor
import com.nyi.network.auth.AuthTokenStore
import com.nyi.network.datasource.movie.movieDataSourceFakeImpl
import com.nyi.network.datasource.movie.movieDataSourceRealImpl
import com.nyi.network.exception.NetworkExceptionInterceptor
import com.nyi.network.service.MovieService
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Vincent on 2/18/19
 */
@Module(includes = [NetworkModule.Providers::class])
abstract class NetworkModule {

  @Binds
  abstract fun movieDataSource(movieNetworkDataSource: movieDataSourceRealImpl): MovieNetworkDataSource

  @Module
  internal object Providers {

    @JvmStatic
    @Provides @Singleton
    fun okHttpClient(): OkHttpClient {

      val builder = OkHttpClient.Builder()

      val loggerInterceptor = HttpLoggingInterceptor()

      if (BuildConfig.DEBUG) {
        loggerInterceptor.level = BODY
      } else {
        loggerInterceptor.level = NONE
      }

      val networkExceptionInterceptor = NetworkExceptionInterceptor()

      //val authTokenInterceptor = AuthTokenInterceptor(authTokenStore)

      builder
        //.addInterceptor(authTokenInterceptor)
        .addInterceptor(loggerInterceptor)
        .addInterceptor(networkExceptionInterceptor)

      return builder.build()
    }

    @JvmStatic
    @Provides @Singleton fun retrofit(okHttpClient: OkHttpClient): Retrofit {
      val builder = Retrofit.Builder()
      val moshi = Moshi.Builder().build()

      builder.baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

      return builder.build()
    }

    @JvmStatic
    @Provides fun movieService(retrofit: Retrofit): MovieService {
      return retrofit.create(MovieService::class.java)
    }

  }

}