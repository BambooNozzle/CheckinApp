package com.cesaanwar.checkinapp.di

import android.content.Context
import androidx.room.Room
import com.cesaanwar.checkinapp.data.source.local.CheckinDatabase
import com.cesaanwar.checkinapp.data.source.local.LocalStoreDataSource
import com.cesaanwar.checkinapp.data.source.remote.service.LoginService
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CheckinDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CheckinDatabase::class.java,
            "checkin.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context,
                    showNotification = true))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()

    @Singleton
    @Provides
    fun provideLoginRetrofitService(
        client: OkHttpClient
    ): LoginService {
        return Retrofit.Builder().
                baseUrl("https://dev.pitjarus.co/api/sariroti_md/index.php/").
                client(client).
                addConverterFactory(GsonConverterFactory.create()).
                build().
                create(LoginService::class.java)
    }

}