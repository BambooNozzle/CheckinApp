package com.cesaanwar.checkinapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.domain.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.cesaanwar.checkinapp.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
): ViewModel() {

    private val _loginLiveData = MutableLiveData<Result<String>>()
    val loginLiveData : LiveData<Result<String>> = _loginLiveData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = postLoginUseCase.postLogin(username, password)
            _loginLiveData.value = result
        }
    }

}