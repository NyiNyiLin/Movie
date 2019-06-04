package com.nyi.appbase.core.mvp

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseViewModel<viewable : Viewable> : ViewModel(), Presentable<viewable> {

  val loggingExceptionHandler = CoroutineExceptionHandler { _, t ->
    Timber.i(t)
  }
  protected val parentJob = Job()
  protected val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
  protected val scope = CoroutineScope(coroutineContext) + loggingExceptionHandler


  protected var view: viewable? = null

  protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

  override fun attachView(viewable: viewable) {
    this.view = viewable
  }

  override fun detachView() {
    this.view = null
  }

  override fun onCleared() {
    coroutineContext.cancel()
    compositeDisposable.dispose()
    super.onCleared()
  }

}