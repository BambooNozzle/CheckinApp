package com.cesaanwar.checkinapp.uimodel

data class StoreUIModel(
    val storeId: String = "",
    val storeName: String = "",
    val channelName: String = "",
    val accountName: String = ""
) {
    fun getSubTitle() = "$channelName - $accountName"
}