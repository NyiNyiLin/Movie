package com.nyi.network.exception

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

/**
 * Created by Vincent on 2/23/19
 */
class NetworkExceptionInterceptor : Interceptor {

  override fun intercept(chain: Chain): Response {
    
    val response = chain.proceed(chain.request())
    if (response.isSuccessful) {
      return response
    } else {
      throw NetworkException(response.body(), response.code())
    }
  }

}