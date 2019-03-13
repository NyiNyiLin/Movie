package com.nyi.appbase.exception

import android.content.Context
import com.nyi.appbase.R
import com.nyi.network.exception.NetworkException
import com.nyi.network.exception.NetworkExceptionMessageFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 2/26/19
 */
class NetworkExceptionMessageFactoryRealImpl @Inject constructor(
  private val context: Context
) :
  NetworkExceptionMessageFactory {

  override fun getErrorMessage(networkException: NetworkException): CharSequence {
    when (networkException.errorCode) {
      400 -> return context.getString(R.string.error_http)
      404 -> return context.getString(R.string.error_server_404)
      500 -> return context.getString(R.string.error_server_500)
    }

    return context.getString(R.string.error_generic)
  }

  override fun getErrorMessage(unknownHostException: UnknownHostException): CharSequence {
    return context.getString(R.string.error_no_internet)
  }

  override fun getErrorMessage(socketTimeoutException: SocketTimeoutException): CharSequence {
    return context.getString(R.string.error_socket_timeout)
  }

  override fun getErrorMessage(connectException: ConnectException): CharSequence {
    return context.getString(R.string.error_no_internet)
  }

}
