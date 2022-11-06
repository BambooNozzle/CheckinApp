package com.cesaanwar.checkinapp.di

import com.cesaanwar.checkinapp.data.source.StoreDataSource
import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.data.source.local.CheckinDatabase
import com.cesaanwar.checkinapp.data.source.local.LocalStoreDataSource
import com.cesaanwar.checkinapp.data.source.repositoryimpl.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import javax.sql.DataSource

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class StoreLocalDataSource

    @Singleton
    @StoreLocalDataSource
    @Provides
    fun provideLocalStoreDataSource(
        database: CheckinDatabase
    ): StoreDataSource {
        return LocalStoreDataSource(
            storeDao = database.storeDao()
        )
    }

    @Provides
    @Singleton
    fun provideStoreRepository(
        @StoreLocalDataSource localStoreDataSource: StoreDataSource
    ): StoreRepository {
        return StoreRepositoryImpl(localStoreDataSource)
    }

}