package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.VisitRepository
import javax.inject.Inject

class GetLatestVisitUseCase @Inject constructor(
    private val visitRepository: VisitRepository
) {



}