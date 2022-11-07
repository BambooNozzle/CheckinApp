package com.cesaanwar.checkinapp.data.source.local

import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.data.source.StoreDataSource
import com.cesaanwar.checkinapp.data.source.local.dao.StoreDao

class LocalStoreDataSource(
    val storeDao: StoreDao
): StoreDataSource {
    override suspend fun insertStores(stores: List<Store>) = storeDao.insertStores(stores)

    override suspend fun getAllStores(): List<Store> = storeDao.getAllStores()

    override suspend fun getStoreByLocalStoreIdAndStoreId(
        localStoreId: Long,
        storeId: String
    ): Store = storeDao.getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)

}