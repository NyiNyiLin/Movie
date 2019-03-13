package com.nyi.network.exception

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Vincent on 2/23/19
 */
interface NetworkExceptionMessageFactory {

  fun getErrorMessage(networkException: NetworkException): CharSequence

  fun getErrorMessage(unknownHostException: UnknownHostException): CharSequence

  fun getErrorMessage(socketTimeoutException: SocketTimeoutException): CharSequence

  fun getErrorMessage(connectException: ConnectException): CharSequence

}