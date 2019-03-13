package com.nyi.movie.di

import android.app.Application
import com.nyi.movie.MovieApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =[AppModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MovieApp)
}