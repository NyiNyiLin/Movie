package com.nyi.network.auth

import android.content.Context
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Created by Vincent on 2/23/19
 */
class RequestAuthenticator : Authenticator {

  lateinit var context: Context

  override fun authenticate(route: Route, response: Response): Request? {

    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}