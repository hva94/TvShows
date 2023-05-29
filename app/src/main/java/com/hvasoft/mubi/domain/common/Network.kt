package com.hvasoft.mubi.domain.common

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Allows to make a request in a safe way catching possible errors
 * and prints log with Logcat
 * @return Result<T>: returns a Result wrapper with the given expected data
 */

private const val TAG = "Network"
suspend fun <T : Any> makeSafeRequest(
    execute: suspend () -> Response<T>
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                Result.Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            Log.e(TAG, "makeSafeRequest: $e")
            Result.Exception(e)
        }
    }
}