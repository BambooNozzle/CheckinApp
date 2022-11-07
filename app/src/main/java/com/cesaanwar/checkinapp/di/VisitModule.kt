package com.cesaanwar.checkinapp.di

import com.cesaanwar.checkinapp.data.source.VisitDataSource
import com.cesaanwar.checkinapp.data.source.VisitRepository
import com.cesaanwar.checkinapp.data.source.local.CheckinDatabase
import com.cesaanwar.checkinapp.data.source.local.LocalVisitDataSource
import com.cesaanwar.checkinapp.data.source.repositoryimpl.VisitRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VisitModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class VisitLocalDataStore

    @Singleton
    @VisitLocalDataStore
    @Provides
    fun provideLocalVisitDataSource(
        database: CheckinDatabase
    ): VisitDataSource {
        return LocalVisitDataSource(database.visitDao())
    }

    @Provides
    @Singleton
    fun provideVisitRepository(
        @VisitLocalDataStore localVisitDataSource: VisitDataSource
    ): VisitRepository {
        return VisitRepositoryImpl(localVisitDataSource)
    }

}