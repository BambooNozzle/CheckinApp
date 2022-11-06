package com.cesaanwar.checkinapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesaanwar.checkinapp.domain.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase
): ViewModel() {

    fun login(username: String, password: String) {
        viewModelScope.launch {
            postLoginUseCase.postLogin(username, password)
        }
    }

}