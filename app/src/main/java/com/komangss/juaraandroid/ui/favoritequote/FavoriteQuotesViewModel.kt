package com.komangss.juaraandroid.ui.favoritequote

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
class FavoriteQuotesViewModel @Inject constructor(private val quotableRepository: QuotableRepository) : ViewModel() {
    val quoteList: LiveData<Resource<List<Quote>>> = liveData {
        emitSource(quotableRepository.getFavoriteQuotes().asLiveData())
    }

    suspend fun unFavoriteThisQuote(quote: Quote): Resource<Int> {
        return quotableRepository.unFavoriteThisQuote(quote)
    }
}