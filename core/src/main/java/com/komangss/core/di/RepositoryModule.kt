package com.komangss.core.di

import com.komangss.core.data.QuotableRepository
import com.komangss.core.di.DatabaseModule
import com.komangss.core.di.NetworkModule
import com.komangss.core.domain.repository.IQuotableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(quotableRepository : QuotableRepository): IQuotableRepository
}