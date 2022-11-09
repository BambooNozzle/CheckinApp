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

    override suspend fun updateVisitData(visits: List<Visit>): Int {
        return visitDao.updateVisitData(visits)
    }

    override suspend fun getVisitByStoreIdAndTime(visitDateMilis: Long): List<Visit> {
        return visitDao.getVisitsByStoreAndTime(visitDateMilis)
    }

    override suspend fun getLatestVisitsByStore(localStoreId: Long, storeId: String): Visit? {
        return visitDao.getLatestVisitsByStore(localStoreId, storeId)
    }

    override suspend fun getActiveVisit(): List<Visit> {
        return visitDao.getActiveVisit()
    }

}