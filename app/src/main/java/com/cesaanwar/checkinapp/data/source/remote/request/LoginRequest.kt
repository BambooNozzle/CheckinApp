package com.cesaanwar.checkinapp.data.source.remote.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject

class LoginRequest(
    private val username: String,
    private val password: String
): Request {

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }

    override fun getRequestBody(): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(USERNAME, username)
            .addFormDataPart(PASSWORD, password)
            .build()
    }
}
