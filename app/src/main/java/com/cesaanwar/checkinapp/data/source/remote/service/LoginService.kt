package com.cesaanwar.checkinapp.data.source.remote.service

import com.cesaanwar.checkinapp.data.source.remote.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login/loginTest")
    suspend fun getLoginData(@Body body: RequestBody): LoginResponse

}