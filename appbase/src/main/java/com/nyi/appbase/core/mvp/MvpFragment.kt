package com.nyi.appbase.core.mvp

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nyi.appbase.core.BaseFragment
import com.nyi.appbase.di.Injectable
import com.nyi.domainn.exception.GenericErrorMessageFactory
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Vincent on 12/6/18
 */
abstract class MvpFragment<V : Viewable, VM : BaseViewModel<V>> :
  BaseFragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  @Inject
  lateinit var genericErrorMessageFactory: GenericErrorMessageFactory

  protected abstract val viewModel: VM

  override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    try {
      viewModel.attachView(this as V)
    } catch (exception: Exception) {
      Timber.e(InvalidMvpImplementationException())
      throw InvalidMvpImplementationException()
    }
  }

  override fun onDestroyView() {
    viewModel.detachView()
    super.onDestroyView()
  }

  /**
   * Helper function for easily init of viewModel
   */
  protected inline fun <reified VM : BaseViewModel<V>> contractedViewModel(): Lazy<VM> =
    ViewModelLazy(VM::class)

  inner class ViewModelLazy<VM : androidx.lifecycle.ViewModel>(
    private val viewModelClass: KClass<VM>
  ) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
      get() {
        var viewModel = cached
        if (viewModel == null) {
          viewModel =
            ViewModelProvider(
              this@MvpFragment,
              viewModelFactory
            ).get(viewModelClass.java)
          cached = viewModel
        }
        return viewModel
      }

    override fun isInitialized() = cached != null
  }

}