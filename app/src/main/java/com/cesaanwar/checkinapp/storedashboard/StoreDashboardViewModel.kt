package com.cesaanwar.checkinapp.storedashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Visit
import com.cesaanwar.checkinapp.domain.DeactivateVisitUseCase
import com.cesaanwar.checkinapp.domain.GetActiveVisitUseCase
import com.cesaanwar.checkinapp.domain.GetDashboardDetailUseCase
import com.cesaanwar.checkinapp.uimodel.StoreDashboardUIModel
import com.cesaanwar.checkinapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreDashboardViewModel @Inject constructor(
    private val getActiveVisitUseCase: GetActiveVisitUseCase,
    private val getDashboardDetailUseCase: GetDashboardDetailUseCase,
    private val deactivateVisitUseCase: DeactivateVisitUseCase
): ViewModel() {

    private val _activeVisitLiveData = MutableLiveData<Result<List<Visit>>>()
    val activeVisitLiveData : LiveData<Result<List<Visit>>> = _activeVisitLiveData

    private val _visitDataLiveData = MutableLiveData<Result<StoreDashboardUIModel>>()
    val visitDataLiveData : LiveData<Result<StoreDashboardUIModel>> = _visitDataLiveData

    private val _leaveVisitEventLiveData = MutableLiveData<Event<Boolean>>()
    val leaveVisitEventLiveData : LiveData<Event<Boolean>> = _leaveVisitEventLiveData

    fun fetchActiveVisit() {
        viewModelScope.launch {
            val visit = getActiveVisitUseCase.getActiveVisit()
            _activeVisitLiveData.value = visit
        }
    }

    fun getActiveVisitData(localStoreId: Long, storeId: String) {
        viewModelScope.launch {
            val visitData = getDashboardDetailUseCase.getStoreDetailForDashboard(localStoreId, storeId)
            _visitDataLiveData.value = visitData
        }
    }

    fun leaveVisit() {
        viewModelScope.launch {
            val result = deactivateVisitUseCase.deactivateVisits()
            if (result is Result.Success) {
                _leaveVisitEventLiveData.value = Event(true)
            } else {
                _leaveVisitEventLiveData.value = Event(false)
            }
        }
    }

}