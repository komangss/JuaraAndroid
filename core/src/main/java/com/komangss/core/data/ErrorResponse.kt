package com.komangss.core.data

data class ErrorResponse(
    val errorMessage: String,
val causes: Map<String, String> = emptyMap() //this is for errors on specific field on a form
)

