package com.komangss.core.domain.repository

import com.komangss.core.data.Resource
import com.komangss.core.domain.model.QuoteList
import kotlinx.coroutines.flow.Flow

interface IQuotableRepository {
    suspend fun getQuotes() : Flow<Resource<QuoteList>>

    suspend fun getFavoriteQuotes() : Flow<Resource<QuoteList>>
}