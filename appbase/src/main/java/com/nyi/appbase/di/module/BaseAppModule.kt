package com.nyi.appbase.di.module

import android.app.Application
import android.content.Context
import com.nyi.appbase.UIThread
import com.nyi.appbase.di.viewmodel.ViewModelFactoryModule
import com.nyi.appbase.exception.GenericErrorMessageFactoryRealImpl
import com.nyi.appbase.exception.NetworkExceptionMessageFactoryRealImpl
import com.nyi.data.JobExecutor
import com.nyi.domainn.exception.GenericErrorMessageFactory
import com.nyi.domainn.executor.PostExecutionThread
import com.nyi.domainn.executor.ThreadExecutor
import com.nyi.network.exception.NetworkExceptionMessageFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [BaseAppModule.Provider::class, ViewModelFactoryModule::class, RepositoryModule::class])
abstract class BaseAppModule {

  @Binds
  abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

  @Binds
  abstract fun postExecutionThread(uiThread: UIThread): PostExecutionThread

  @Binds
  abstract fun genericErrorMessageFactory(genericErrorMessageFactory: GenericErrorMessageFactoryRealImpl): GenericErrorMessageFactory

  @Binds
  abstract fun networkErrorMessageFactory(networkExceptionMessageFactory: NetworkExceptionMessageFactoryRealImpl): NetworkExceptionMessageFactory

  @Module
  object Provider {

    @Provides @JvmStatic @Singleton fun context(application: Application): Context {
      return application.applicationContext
    }

  }

}

