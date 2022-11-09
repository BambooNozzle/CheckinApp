package com.cesaanwar.checkinapp.data.source

import com.cesaanwar.checkinapp.data.Visit

interface VisitDataSource {

    suspend fun registerVisit(visit: Visit)

    suspend fun updateVisitData(visits: List<Visit>): Int

    suspend fun getVisitByStoreIdAndTime(visitDateMilis: Long): List<Visit>

    suspend fun getLatestVisitsByStore(localStoreId: Long, storeId: String): Visit?

    suspend fun getActiveVisit(): List<Visit>

}