package com.komangss.core.data.source.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.komangss.core.data.source.local.dao.QuotableDao
import com.komangss.core.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: QuotableDao) {
    suspend fun insertQuote(quote: Quote) : Long {
        return dao.insertQuote(quote)
    }

    suspend fun getQuotes() : Flow<List<Quote>> {
        return dao.getQuotes()
    }
    suspend fun deleteQuote(quote: Quote) : Int {
        return dao.deleteQuote(quote)
    }
}