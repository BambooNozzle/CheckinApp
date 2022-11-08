package com.cesaanwar.checkinapp.uimodel

data class StoreDetailUIModel(
    val localStoreId: Long = 0L,
    val storeId: String = "",
    val storeName: String = "",
    val address: String = "",
    val longitude: Double = 0.0,
    val latitude : Double = 0.0,
    val area: String = "",
    val region: String = "",
    val distance: Double = 0.0,
    val lastVisit: String = ""
)
