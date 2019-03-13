package com.nyi.network.exception

import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by Vincent on 2/23/19
 */
data class NetworkException constructor(
  val errorBody: ResponseBody? = null,
  var errorCode: Int = 0
) : IOException()
