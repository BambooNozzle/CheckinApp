package com.cesaanwar.checkinapp.data.source.repositoryimpl

import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.data.source.StoreDataSource
import com.cesaanwar.checkinapp.data.source.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoreRepositoryImpl(
    private val localStoreDataSource: StoreDataSource
): StoreRepository {

    override suspend fun insertStores(stores: List<Store>) = withContext(Dispatchers.IO) {
        localStoreDataSource.insertStores(stores)
    }

    override suspend fun getAllStores(): List<Store> = withContext(Dispatchers.IO) {
        localStoreDataSource.getAllStores()
    }

    override suspend fun getStoreByLocalStoreIdAndStoreId(
        localStoreId: Long,
        storeId: String
    ): Store = withContext(Dispatchers.IO) {
        localStoreDataSource.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
    }

}