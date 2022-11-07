package com.cesaanwar.checkinapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentDateMilisWithoutTime(): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateWithoutTime: Date? = sdf.parse(sdf.format(Date()))
        return dateWithoutTime?.time ?: -1L
    }

}