package com.komangss.core.domain.model

data class AuthorList (
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val lastItemIndex: Int?,
    val results: List<Author>
)