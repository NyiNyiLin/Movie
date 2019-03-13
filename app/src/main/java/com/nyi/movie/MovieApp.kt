package com.nyi.movie

import android.app.Activity
import android.app.Application
import com.nyi.appbase.di.AppInjector
import com.nyi.movie.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MovieApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        AppInjector.initAutoInjection(this)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            //Stetho.initializeWithDefaults(this)
        }

        //AndroidThreeTen.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

}