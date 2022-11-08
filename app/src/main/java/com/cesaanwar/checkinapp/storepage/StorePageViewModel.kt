package com.cesaanwar.checkinapp.storepage

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.domain.GetSpecificStoreUseCase
import com.cesaanwar.checkinapp.domain.StoreVisitUseCase
import com.cesaanwar.checkinapp.uimodel.StoreDetailUIModel
import com.cesaanwar.checkinapp.uimodel.StoreListUIModel
import com.cesaanwar.checkinapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorePageViewModel @Inject constructor(
    private val getSpecificStoreUseCase: GetSpecificStoreUseCase,
    private val storeVisitUseCase: StoreVisitUseCase
): ViewModel() {

    private val _storeLiveData = MutableLiveData<Result<StoreDetailUIModel>>()
    val storeLiveData: LiveData<Result<StoreDetailUIModel>> = _storeLiveData

    private val _storeVisitEventLiveData = MutableLiveData<Event<Boolean>>()
    val storeVisitEventLiveData : LiveData<Event<Boolean>> = _storeVisitEventLiveData

    fun getStoreByLocalStoreIdAndStoreId(
        localStoreId: Long, storeId: String
    ) {
        viewModelScope.launch {
            val result = getSpecificStoreUseCase
                .getStoreByLocalStoreIdAndStoreId(localStoreId, storeId)
            _storeLiveData.value = result
        }
    }

    fun visitStore() {
        viewModelScope.launch {
            _storeLiveData.value?.let {
                if (it is Result.Success) {
                    val data = it.data
                    val result = storeVisitUseCase.storeVisitData(data.localStoreId, data.storeId)
                    if (result is Result.Success) {
                        _storeVisitEventLiveData.value = Event(true)
                    } else {
                        _storeVisitEventLiveData.value = Event(false)
                    }
                }
            }
        }
    }

}