package com.cesaanwar.checkinapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cesaanwar.checkinapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _goToStoreListEvent = MutableLiveData<Event<Boolean>>()
    val goToStoreListEvent: LiveData<Event<Boolean>> = _goToStoreListEvent

    fun triggerVisit() {
        _goToStoreListEvent.value = Event(true)
    }


}