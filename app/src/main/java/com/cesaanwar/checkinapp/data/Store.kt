package com.cesaanwar.checkinapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "store")
data class Store(
    @PrimaryKey
    @SerializedName("store_id")
    val storeId: String,
    @SerializedName("store_name")
    val storeName: String,
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("area_id")
    val areaId: String,
    @SerializedName("area_name")
    val areaName: String,
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("channel_name")
    val channelName: String,
    @SerializedName("dc_id")
    val dcId: String,
    @SerializedName("dc_name")
    val dcName: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("region_id")
    val regionId: String,
    @SerializedName("region_name")
    val regionName: String,
    @SerializedName("store_code")
    val storeCode: String,
    @SerializedName("subchannel_id")
    val subchannelId: String,
    @SerializedName("subchannel_name")
    val subchannelName: String
)