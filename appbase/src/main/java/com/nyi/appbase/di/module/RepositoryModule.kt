package com.nyi.appbase.di.module

import com.nyi.data.repositoryimpl.MovieRepositoryRealImpl
import com.nyi.domainn.repository.MovieRepository
import com.nyi.network.di.NetworkModule
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun movieRepository(movieRepository: MovieRepositoryRealImpl): MovieRepository

}