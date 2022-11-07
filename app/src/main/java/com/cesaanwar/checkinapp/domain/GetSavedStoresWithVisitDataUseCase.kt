package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.uimodel.StoreUIModel
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.source.VisitRepository
import com.cesaanwar.checkinapp.uimodel.mapper.StoreToStoreUIModelMapper
import com.cesaanwar.checkinapp.util.DateHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSavedStoresWithVisitDataUseCase @Inject constructor(
    private val storeRepository: StoreRepository,
    private val visitRepository: VisitRepository
) {

    suspend fun getAllSavedStores(): Result<List<StoreUIModel>> {
        return try {
            return withContext(Dispatchers.IO) {
                val defers = mutableListOf(
                    async { storeRepository.getAllStores() },
                    async { visitRepository.getVisitByStoreIdAndTime("1", DateHelper.getCurrentDateMilisWithoutTime()) }
                )
                val results = defers.awaitAll()
                val stores = mutableListOf<Store>()
                val visits = HashSet<String>()
                results.forEach { items ->
                    val first = items.firstOrNull()
                    first?.let { item ->
                        when (item) {
                            is Store -> stores.addAll(items as List<Store>)
                            is Visit -> visits.addAll(getVisitSet(items as List<Visit>))
                        }
                    }
                }
                Success(StoreToStoreUIModelMapper.mapStoreToStoreUIModel(stores, visits))
            }
        } catch (e: Exception) {
            Error(e)
        }
    }

    private fun getVisitSet(visits: List<Visit>): Set<String> {
        val set = HashSet<String>()
        visits.forEach {
            set.add(
                "${it.localStoreId};${it.storeId}"
            )
        }
        return set
    }

}