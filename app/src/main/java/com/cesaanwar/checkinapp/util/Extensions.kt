package com.cesaanwar.checkinapp.util

fun Double.toStoreDistanceInfo(): String {
    if (this > 100) {
        return ">100m"
    }
    return String.format("%.1fm", this)
}