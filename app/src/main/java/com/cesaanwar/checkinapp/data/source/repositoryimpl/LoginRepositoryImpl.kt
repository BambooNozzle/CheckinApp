package com.cesaanwar.checkinapp.data.source.repositoryimpl

import com.cesaanwar.checkinapp.data.source.LoginDataSource
import com.cesaanwar.checkinapp.data.source.LoginRepository
import com.cesaanwar.checkinapp.data.source.remote.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {
    override suspend fun postLogin(requestBody: RequestBody): LoginResponse = withContext(
        Dispatchers.IO
    ) {
        loginDataSource.postLogin(requestBody)
    }
}