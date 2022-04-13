package com.komangss.core.data

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(
        val code: Int? = null,
        val error: ErrorResponse? = null
    ) : Resource<Nothing>()

    object InProgress : Resource<Nothing>()
}