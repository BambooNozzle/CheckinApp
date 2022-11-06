package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.uimodel.StoreUIModel
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.uimodel.mapper.StoreToStoreUIModelMapper
import javax.inject.Inject

class GetSavedStoresUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend fun getAllSavedStores(): Result<List<StoreUIModel>> {
        return try {
            Success(StoreToStoreUIModelMapper.mapStoreToStoreUIModel(
                storeRepository.getAllStores()
            ))
        } catch (e: Exception) {
            Error(e)
        }
    }

}