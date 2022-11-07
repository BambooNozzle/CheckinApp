package com.cesaanwar.checkinapp.storelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.domain.GetSavedStoresWithVisitDataUseCase
import com.cesaanwar.checkinapp.uimodel.StoreUIModel
import com.cesaanwar.checkinapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getSavedStoresWithVisitDataUseCase: GetSavedStoresWithVisitDataUseCase
): ViewModel() {

    private val _storeUIModelLiveData = MutableLiveData<Result<List<StoreUIModel>>>()
    val storeUIModelLiveData : LiveData<Result<List<StoreUIModel>>> = _storeUIModelLiveData

    private val _storeVisitEventLiveData = MutableLiveData<Event<StoreUIModel>>()
    val storeVisitEventLiveData : LiveData<Event<StoreUIModel>> = _storeVisitEventLiveData

    fun getStores() {
        viewModelScope.launch {
            val result = getSavedStoresWithVisitDataUseCase.getAllSavedStores()
            _storeUIModelLiveData.value = result
        }
    }

    fun visitStore(storeUIModel: StoreUIModel) {
        _storeVisitEventLiveData.value = Event(storeUIModel)
    }

}