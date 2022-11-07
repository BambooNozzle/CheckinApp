package com.cesaanwar.checkinapp.uimodel

data class StoreUIModel(
    val localStoreId: Long = 0L,
    val storeId: String = "",
    val storeName: String = "",
    val channelName: String = "",
    val accountName: String = "",
    val hasBeenVisited: Boolean = false
) {
    fun getSubTitle() = "$channelName - $accountName"
}