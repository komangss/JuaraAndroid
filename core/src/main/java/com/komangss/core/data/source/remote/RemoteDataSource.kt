package com.komangss.core.data.source.remote

import com.komangss.core.data.source.remote.network.QuotableServices
import com.komangss.core.data.source.remote.response.ApiResponse
import com.komangss.core.data.source.remote.response.safeApiCall
import com.komangss.core.domain.model.AuthorList
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(private val quotableServices: QuotableServices) {
    suspend fun getRandomQuote(): Flow<ApiResponse<Quote>> {
        return safeApiCall(Dispatchers.IO) {quotableServices.getRandomQuote()}
    }

    suspend fun getQuoteById(id : Int): Flow<ApiResponse<Quote>> {
        return safeApiCall(Dispatchers.IO) {quotableServices.getQuoteById(id)}
    }

    suspend fun getQuoteList(): Flow<ApiResponse<QuoteList>> {
        return safeApiCall(Dispatchers.IO) {quotableServices.getQuoteList()}
    }

    suspend fun getAuthorList(): Flow<ApiResponse<AuthorList>> {
        return safeApiCall(Dispatchers.IO) {quotableServices.getAuthorList()}
    }
}