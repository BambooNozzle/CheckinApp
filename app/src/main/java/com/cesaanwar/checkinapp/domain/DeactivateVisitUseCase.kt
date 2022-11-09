package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.VisitRepository
import javax.inject.Inject
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error

class DeactivateVisitUseCase @Inject constructor(
    val visitRepository: VisitRepository
) {

    suspend fun deactivateVisits(): Result<Boolean> {
        return try {
            val activeVisit = visitRepository.getActiveVisit()
            activeVisit.forEach {
                it.isActive = false
            }
            visitRepository.updateVisitData(activeVisit)
            Success(true)
        } catch (e: Exception) {
            Error(e)
        }
    }

}