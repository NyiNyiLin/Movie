package com.nyi.network.auth

/**
 * Created by Vincent on 2/23/19
 */
interface AuthTokenStore {

  fun getAuthToken(): String?

  fun updateAuthToken(authToken: String)
}