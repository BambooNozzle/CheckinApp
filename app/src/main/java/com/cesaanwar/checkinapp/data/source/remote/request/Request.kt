package com.cesaanwar.checkinapp.data.source.remote.request

import okhttp3.RequestBody

interface Request {

    fun getRequestBody(): RequestBody

}