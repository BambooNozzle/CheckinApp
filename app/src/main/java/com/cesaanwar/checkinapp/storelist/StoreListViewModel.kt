package com.cesaanwar.checkinapp.storelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.domain.GetSavedStoresUseCase
import com.cesaanwar.checkinapp.uimodel.StoreUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    val getSavedStoresUseCase: GetSavedStoresUseCase
): ViewModel() {

    private val _storeUIModelLiveData = MutableLiveData<Result<List<StoreUIModel>>>()
    val storeUIModelLiveData : LiveData<Result<List<StoreUIModel>>> = _storeUIModelLiveData

    fun getStores() {
        viewModelScope.launch {
            val result = getSavedStoresUseCase.getAllSavedStores()
            _storeUIModelLiveData.value = result
        }
    }

}