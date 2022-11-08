package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.source.VisitRepository
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import javax.inject.Inject

class StoreVisitUseCase @Inject constructor(
    private val visitRepository: VisitRepository
) {

    suspend fun storeVisitData(localStoreId: Long, storeId: String): Result<Boolean> {
        return try {
            val visitData = Visit(
                localStoreId = localStoreId,
                storeId = storeId,
                visitTimeMilis = System.currentTimeMillis(),
                isActive = true
            )
            visitRepository.registerVisit(visitData)
            Success(true)
        } catch (e: Exception) {
            Error(e)
        }
    }

}