package com.komangss.core.data.source.remote.network

import com.komangss.core.domain.model.AuthorList
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Path

interface QuotableServices {
    @GET("random")
    suspend fun getRandomQuote(): Quote

    @GET("quotes")
    suspend fun getQuoteList() : QuoteList

    @GET("quotes/{id}")
    suspend fun getQuoteById(id : Int) : Quote

    @GET("authors")
    suspend fun getAuthorList() : AuthorList

}