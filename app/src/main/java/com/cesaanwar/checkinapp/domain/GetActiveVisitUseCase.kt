package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import com.cesaanwar.checkinapp.data.source.VisitRepository
import javax.inject.Inject

class GetActiveVisitUseCase @Inject constructor(
    private val visitRepository: VisitRepository
) {

    suspend fun getActiveVisit(): Result<List<Visit>> {
        return try {
            val visits = visitRepository.getActiveVisit()
            Success(visits)
        } catch (e: Exception) {
            Error(e)
        }
    }

}