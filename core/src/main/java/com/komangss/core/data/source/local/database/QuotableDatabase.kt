package com.komangss.core.data.source.local.database

import androidx.room.Database
import com.komangss.core.data.source.local.dao.QuotableDao
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList

@Database(entities = [Quote::class], version = 1)
abstract class QuotableDatabase {
    abstract val quotableDao : QuotableDao
}