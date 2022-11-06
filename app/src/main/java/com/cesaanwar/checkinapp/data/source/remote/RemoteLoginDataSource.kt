package com.cesaanwar.checkinapp.data.source.remote

import com.cesaanwar.checkinapp.data.source.LoginDataSource
import com.cesaanwar.checkinapp.data.source.remote.response.LoginResponse
import com.cesaanwar.checkinapp.data.source.remote.service.LoginService
import okhttp3.RequestBody

class RemoteLoginDataSource(
    private val service: LoginService
): LoginDataSource {
    override suspend fun postLogin(requestBody: RequestBody): LoginResponse
                                                = service.getLoginData(requestBody)
}