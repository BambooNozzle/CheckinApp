package com.cesaanwar.checkinapp.di

import com.cesaanwar.checkinapp.data.source.LoginDataSource
import com.cesaanwar.checkinapp.data.source.LoginRepository
import com.cesaanwar.checkinapp.data.source.remote.RemoteLoginDataSource
import com.cesaanwar.checkinapp.data.source.remote.service.LoginService
import com.cesaanwar.checkinapp.data.source.repositoryimpl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginRemoteDataSource

    @Provides
    @LoginRemoteDataSource
    @Singleton
    fun provideRemoteLoginDataSource(
        service: LoginService
    ): LoginDataSource {
        return RemoteLoginDataSource(service)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        @LoginRemoteDataSource remoteLoginDataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(remoteLoginDataSource)
    }

}