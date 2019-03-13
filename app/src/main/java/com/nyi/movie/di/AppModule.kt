package com.nyi.movie.di

import com.nyi.appbase.di.module.BaseAppModule
import com.nyi.movie.feature.home.di.MainFeatureModule
import dagger.Module

@Module(
    includes = [BaseAppModule::class,
    MainFeatureModule::class]
)
abstract class AppModule {

}