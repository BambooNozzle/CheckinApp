package com.cesaanwar.checkinapp.data.source

import com.cesaanwar.checkinapp.data.Store

interface StoreRepository {

    suspend fun insertStores(stores: List<Store>)

    suspend fun getAllStores(): List<Store>

}