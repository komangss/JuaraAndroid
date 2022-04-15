package com.komangss.core.data.source.remote.response

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val value: T): ApiResponse<T>()
    data class NetworkError(
        val code: Int? = null,
        val error: ErrorResponse? = null,
        val exception: Exception? = null
    ): ApiResponse<Nothing>()
    object GenericError: ApiResponse<Nothing>()
}

suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Flow<ApiResponse<T>> {
    return flow {
        try {
            emit(ApiResponse.Success(apiCall.invoke()))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> emit(ApiResponse.GenericError)
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    emit(ApiResponse.NetworkError(code, errorResponse, throwable))
                }
                else -> {
                    emit(ApiResponse.NetworkError(null, null))
                }
            }
        }
    }.flowOn(dispatcher)
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}