package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.data.source.VisitRepository
import com.cesaanwar.checkinapp.uimodel.StoreDetailUIModel
import com.cesaanwar.checkinapp.uimodel.mapper.StoreToStoreUIModelMapper
import javax.inject.Inject

class GetSpecificStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository,
    private val visitRepository: VisitRepository
) {

    suspend fun getStoreByLocalStoreIdAndStoreId(
        localStoreId: Long, storeId: String
    ): Result<StoreDetailUIModel> {
        return try {
            val store = storeRepository.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
            val latestVisit = visitRepository.getLatestVisitsByStore(localStoreId, storeId)
            Success(StoreToStoreUIModelMapper.mapStoreToStoreDetailUIModel(store, latestVisit))
        } catch (e: Exception) {
            Error(e)
        }
    }

}