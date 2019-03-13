package com.nyi.movie.feature.home.di

import androidx.lifecycle.ViewModel
import com.nyi.appbase.di.viewmodel.ViewModelKey
import com.nyi.movie.feature.home.MainActivity
import com.nyi.movie.feature.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainFeatureModule {

  @ContributesAndroidInjector
  abstract fun mainActivity(): MainActivity

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

}