package com.nyi.network.auth

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Vincent on 2/23/19
 */
class AuthTokenInterceptor @Inject constructor(private val authTokenStore: AuthTokenStore) :
  Interceptor {

  override fun intercept(chain: Chain): Response {
    val request = chain.request()

    val originalHeader = request.headers()

    val headerBuilder = originalHeader.newBuilder()

    if (authTokenStore.getAuthToken() != null) {
      headerBuilder.add("Authorization", authTokenStore.getAuthToken())
    }

    val newRequest = request.newBuilder().headers(headerBuilder.build()).build()
    return chain.proceed(newRequest)
  }

}