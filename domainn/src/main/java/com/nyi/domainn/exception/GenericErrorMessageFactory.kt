package com.nyi.domainn.exception

/**
 * Created by Vincent on 12/12/18
 */
interface GenericErrorMessageFactory {

  fun getErrorMessage(throwable: Throwable): CharSequence
}

