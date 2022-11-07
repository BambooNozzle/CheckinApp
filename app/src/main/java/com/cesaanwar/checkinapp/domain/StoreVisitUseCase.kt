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

    suspend fun storeVisitData(store: Store): Result<Boolean> {
        return try {
            val visitData = Visit(
                localStoreId = store.localStoreId,
                storeId = store.storeId,
                visitTimeMilis = System.currentTimeMillis()
            )
            visitRepository.registerVisit(visitData)
            Success(true)
        } catch (e: Exception) {
            Error(e)
        }
    }

}