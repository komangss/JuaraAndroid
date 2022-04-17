package com.komangss.core.di

import android.content.Context
import androidx.room.Room
import com.komangss.core.data.source.local.LocalDataSource
import com.komangss.core.data.source.local.dao.QuotableDao
import com.komangss.core.data.source.local.database.QuotableDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideQuotableDatabase(@ApplicationContext context: Context): QuotableDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("githubUser".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            QuotableDatabase::class.java,
            "quotable_database"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun provideQuotableDao(quotableDatabase: QuotableDatabase): QuotableDao = quotableDatabase.quotableDao

    @Singleton
    @Provides
    fun provideLocalDataSource(quotableDao: QuotableDao): LocalDataSource {
        return LocalDataSource(quotableDao)
    }

}