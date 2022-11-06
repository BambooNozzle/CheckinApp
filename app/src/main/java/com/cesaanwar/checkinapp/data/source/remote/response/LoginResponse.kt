package com.cesaanwar.checkinapp.data.source.remote.response

import com.cesaanwar.checkinapp.data.Store
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("stores")
    val stores: List<Store>
)