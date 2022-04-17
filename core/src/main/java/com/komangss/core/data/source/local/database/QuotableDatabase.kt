package com.komangss.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komangss.core.data.source.local.dao.QuotableDao
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuotableDatabase : RoomDatabase() {
    abstract val quotableDao : QuotableDao
}