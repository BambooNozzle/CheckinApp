package com.cesaanwar.checkinapp.storelist

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.domain.GetSavedStoresWithVisitDataUseCase
import com.cesaanwar.checkinapp.uimodel.StoreListUIModel
import com.cesaanwar.checkinapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getSavedStoresWithVisitDataUseCase: GetSavedStoresWithVisitDataUseCase
): ViewModel() {

    private val _storeListUIModelLiveData = MutableLiveData<Result<List<StoreListUIModel>>>()
    val storeListUIModelLiveData : LiveData<Result<List<StoreListUIModel>>> = _storeListUIModelLiveData

    private val _storeVisitEventLiveData = MutableLiveData<Event<StoreListUIModel>>()
    val storeVisitEventLiveData : LiveData<Event<StoreListUIModel>> = _storeVisitEventLiveData

    fun getStores(location: Location?) {
        viewModelScope.launch {
            val result = getSavedStoresWithVisitDataUseCase.getAllSavedStores(location)
            _storeListUIModelLiveData.value = result
        }
    }

    fun visitStore(storeListUIModel: StoreListUIModel) {
        _storeVisitEventLiveData.value = Event(storeListUIModel)
    }

}