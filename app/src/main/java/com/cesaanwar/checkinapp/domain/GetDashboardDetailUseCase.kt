package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.StoreRepository
import javax.inject.Inject
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.uimodel.StoreDashboardUIModel
import com.cesaanwar.checkinapp.uimodel.mapper.StoreToStoreUIModelMapper

class GetDashboardDetailUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend fun getStoreDetailForDashboard(localStoreId: Long, storeId: String): Result<StoreDashboardUIModel> {
        return try {
            val store = storeRepository.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
            Success(StoreToStoreUIModelMapper.mapStoreToStoreDashboardUIModel(store))
        } catch (e: Exception) {
            Error(e)
        }
    }

}