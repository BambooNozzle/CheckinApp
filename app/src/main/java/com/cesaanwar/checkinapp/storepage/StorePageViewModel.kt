package com.cesaanwar.checkinapp.storepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.domain.GetSpecificStoreUseCase
import com.cesaanwar.checkinapp.domain.StoreVisitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorePageViewModel @Inject constructor(
    private val getSpecificStoreUseCase: GetSpecificStoreUseCase,
    private val storeVisitUseCase: StoreVisitUseCase
): ViewModel() {

    private val _storeLiveData = MutableLiveData<Result<Store>>()
    val storeLiveData: LiveData<Result<Store>> = _storeLiveData

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
                    storeVisitUseCase.storeVisitData(it.data)
                }
            }
        }
    }

}