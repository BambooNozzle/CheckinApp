package com.cesaanwar.checkinapp.uimodel.mapper

import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.uimodel.StoreUIModel

object StoreToStoreUIModelMapper {

    fun mapStoreToStoreUIModel(stores: List<Store>): List<StoreUIModel> {
        val res = mutableListOf<StoreUIModel>()
        stores.forEach {
            res.add(
                StoreUIModel(
                    storeId = it.storeId,
                    storeName = it.storeName,
                    channelName = it.channelName,
                    accountName = it.accountName
                )
            )
        }
        return res
    }

}