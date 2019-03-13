package com.nyi.appbase.exception

import android.content.Context
import com.nyi.appbase.R
import com.nyi.domainn.exception.GenericErrorMessageFactory
import com.nyi.network.exception.NetworkException
import com.nyi.network.exception.NetworkExceptionMessageFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 2/26/19
 */
class GenericErrorMessageFactoryRealImpl @Inject constructor(
  private val context: Context,
  private val networkExceptionMessageFactory: NetworkExceptionMessageFactory
) : GenericErrorMessageFactory {

  override fun getErrorMessage(throwable: Throwable): CharSequence {
    return when (throwable) {
      is UnknownHostException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is SocketTimeoutException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is ConnectException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is NetworkException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      else -> {
        throwable.message ?: context.getString(R.string.error_generic)
      }

    }
  }

}