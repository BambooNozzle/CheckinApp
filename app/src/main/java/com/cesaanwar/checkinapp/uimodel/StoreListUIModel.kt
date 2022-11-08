package com.cesaanwar.checkinapp.uimodel

data class StoreListUIModel(
    val localStoreId: Long = 0L,
    val storeId: String = "",
    val storeName: String = "",
    val channelName: String = "",
    val accountName: String = "",
    val longitude: Double = 0.0,
    val latitude : Double = 0.0,
    var distanceValue: Double = 0.0,
    var distanceInfo: String = "",
    val hasBeenVisited: Boolean = false
) {
    fun getSubTitle() = "$channelName - $accountName"
}