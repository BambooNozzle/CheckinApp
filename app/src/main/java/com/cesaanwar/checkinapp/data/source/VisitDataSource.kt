package com.cesaanwar.checkinapp.data.source

import com.cesaanwar.checkinapp.data.Visit

interface VisitDataSource {

    suspend fun registerVisit(visit: Visit)

    suspend fun getVisitByStoreIdAndTime(storeId: String, visitDateMilis: Long): List<Visit>

}