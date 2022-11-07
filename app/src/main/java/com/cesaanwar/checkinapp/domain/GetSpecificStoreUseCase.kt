package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.data.Store
import javax.inject.Inject

class GetSpecificStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend fun getStoreByLocalStoreIdAndStoreId(
        localStoreId: Long, storeId: String
    ): Result<Store> {
        return try {
            val store = storeRepository.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
            Success(store)
        } catch (e: Exception) {
            Error(e)
        }
    }

}