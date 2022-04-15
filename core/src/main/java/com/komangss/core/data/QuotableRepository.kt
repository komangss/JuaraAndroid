package com.komangss.core.data

import com.komangss.core.data.source.remote.RemoteDataSource
import com.komangss.core.data.source.remote.response.ApiResponse
import com.komangss.core.data.source.remote.response.ErrorResponse
import com.komangss.core.domain.model.QuoteList
import com.komangss.core.domain.repository.IQuotableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuotableRepository(
    private val remoteDataSource: RemoteDataSource
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

    override suspend fun getFavoriteQuotes(): Flow<Resource<QuoteList>> {
        TODO("Not yet implemented")
    }
}