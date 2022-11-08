package com.cesaanwar.checkinapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentDateMilisWithoutTime(): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateWithoutTime: Date? = sdf.parse(sdf.format(Date()))
        return dateWithoutTime?.time ?: -1L
    }

    fun getCurrentDateInfoString(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    fun getDateInfoStringFromMilis(milis: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date(milis))
    }

}