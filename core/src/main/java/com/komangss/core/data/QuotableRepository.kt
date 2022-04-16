package com.komangss.core.data

import com.komangss.core.data.source.local.LocalDataSource
import com.komangss.core.data.source.remote.RemoteDataSource
import com.komangss.core.data.source.remote.response.ApiResponse
import com.komangss.core.data.source.remote.response.ErrorResponse
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.model.QuoteList
import com.komangss.core.domain.repository.IQuotableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuotableRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IQuotableRepository {
    override suspend fun getQuotes(): Flow<Resource<QuoteList>> {
        return flow {
            emit(Resource.InProgress)
            remoteDataSource.getQuoteList().collect {
                when(it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(it.value))
                    }
                    ApiResponse.GenericError -> {
                        emit(Resource.Error(null, ErrorResponse("Generic Error / IO Exception", emptyMap())))
                    }
                    is ApiResponse.NetworkError -> {
                        emit(Resource.Error(it.code, it.error))
                    }
                }
            }
        }
    }

    override suspend fun getFavoriteQuotes(): Flow<Resource<List<Quote>>> {
        return flow {
            try {
                localDataSource.getQuotes().collect{
                    emit(Resource.Success(it))
                }
            } catch (e  : Exception) {
                emit(Resource.Error())
            }
        }
    }

    override suspend fun favoriteThisQuote(quote: Quote): Resource<Long> {
        return try {
            Resource.Success(localDataSource.insertQuote(quote))
        } catch (e : Exception) {
            Resource.Error()
        }
    }

    override suspend fun unFavoriteThisQuote(quote: Quote): Resource<Int> {
        return try {
            Resource.Success(localDataSource.deleteQuote(quote))
        } catch (e : Exception) {
            Resource.Error()
        }
    }
}