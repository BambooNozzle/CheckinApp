package com.cesaanwar.checkinapp.data.source.repositoryimpl

import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.source.VisitDataSource
import com.cesaanwar.checkinapp.data.source.VisitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VisitRepositoryImpl(
    private val localVisitDataSource: VisitDataSource
): VisitRepository {

    override suspend fun registerVisit(visit: Visit) = withContext(Dispatchers.IO) {
        localVisitDataSource.registerVisit(visit)
    }

    override suspend fun getVisitByStoreIdAndTime(
        storeId: String,
        visitDateMilis: Long
    ): List<Visit> = withContext(Dispatchers.IO) {
        localVisitDataSource.getVisitByStoreIdAndTime(storeId, visitDateMilis)
    }

}