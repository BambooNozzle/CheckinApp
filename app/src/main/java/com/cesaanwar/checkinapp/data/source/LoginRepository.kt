package com.cesaanwar.checkinapp.data.source

import com.cesaanwar.checkinapp.data.source.remote.response.LoginResponse
import okhttp3.RequestBody

interface LoginRepository {

    suspend fun postLogin(requestBody: RequestBody): LoginResponse

}