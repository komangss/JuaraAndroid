package com.komangss.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.komangss.core.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotableDao {
    @Insert
    suspend fun insertQuote(quote: Quote) : Long

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : Flow<List<Quote>>

    @Delete
    suspend fun deleteQuote(quote: Quote) : Int
}