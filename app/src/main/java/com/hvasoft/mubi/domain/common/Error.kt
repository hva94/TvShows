package com.hvasoft.mubi.domain.common

import androidx.annotation.StringRes
import com.hvasoft.mubi.R
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException

/**
 * Class to wrap possible errors and handle them properly
 */
sealed class Error {
    object Connectivity : Error()
    object InternetConnection : Error()
    data class NotFoundTvShow(@StringRes val messageResId: Int) : Error()
    data class Unknown(val message: String) : Error()
    class HttpException(@StringRes val messageResId: Int) : Error()
}

/**
 * Convert a exception into a Error validating which exception happened
 * @return Error
 */
fun Exception.toError(): Error = when (this) {
    is UnknownHostException -> Error.InternetConnection
    is IOException -> Error.Connectivity
    is HttpException -> HttpErrors.handleError(code())
    else -> Error.Unknown(message ?: "")
}

/**
 * Convert a throwable into a Error validating which exception happened
 * @return Error
 */
fun Throwable.toError(): Error = when (this) {
    is UnknownHostException -> Error.InternetConnection
    is IOException -> Error.Connectivity
    is HttpException -> HttpErrors.handleError(code())
    else -> Error.Unknown(message ?: "")
}

/**
 * With a specific code int value, uses handle error function to validate and return a http exception Error
 * @return Error
 */
fun Int.validateHttpErrorCode(): Error {
    return HttpErrors.handleError(this)
}

object HttpErrors {
    /**
     * Given a code error, validate and returns a http exception error
     * @param errorCode: value to validate and handle error code
     * @return Error
     */
    fun handleError(errorCode: Int): Error {
        if (errorCode == HttpURLConnection.HTTP_NOT_FOUND) {
            return Error.NotFoundTvShow(R.string.error_not_found)
        }
        val errorResId = when (errorCode) {
            503 -> R.string.error_service_unavailable
            500 -> R.string.error_internal_server
            400 -> R.string.error_invalid_request
            401 -> R.string.error_unauthorized
            else -> R.string.error_unknown
        }
        return Error.HttpException(messageResId = errorResId)
    }
}