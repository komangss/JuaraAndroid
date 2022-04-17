package com.komangss.juaraandroid.ui.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.komangss.core.data.QuotableRepository
import com.komangss.core.data.Resource
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val quotableRepository: QuotableRepository) : ViewModel() {
    val quoteList: LiveData<Resource<QuoteList>> = liveData {
        emitSource(quotableRepository.getQuotes().asLiveData())
    }

    suspend fun favoriteThisQuote(quote: Quote): Resource<Long> {
        return quotableRepository.favoriteThisQuote(quote)
    }
}

