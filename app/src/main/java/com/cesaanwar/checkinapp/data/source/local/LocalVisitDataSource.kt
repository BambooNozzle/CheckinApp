package com.cesaanwar.checkinapp.data.source.local

import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.source.VisitDataSource
import com.cesaanwar.checkinapp.data.source.local.dao.VisitDao

class LocalVisitDataSource(
    private val visitDao: VisitDao
): VisitDataSource {

    override suspend fun registerVisit(visit: Visit) {
        visitDao.insertVisit(visit)
    }

    override suspend fun getVisitByStoreIdAndTime(
        storeId: String,
        visitDateMilis: Long
    ): List<Visit> {
        return visitDao.getVisitsByStoreAndTime(storeId, visitDateMilis)
    }

}