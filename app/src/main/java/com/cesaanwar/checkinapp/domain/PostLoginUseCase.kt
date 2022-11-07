package com.cesaanwar.checkinapp.domain

import com.cesaanwar.checkinapp.data.source.LoginRepository
import com.cesaanwar.checkinapp.data.source.StoreRepository
import com.cesaanwar.checkinapp.data.source.remote.request.LoginRequest
import com.cesaanwar.checkinapp.data.Result
import com.cesaanwar.checkinapp.data.Result.Success
import com.cesaanwar.checkinapp.data.Result.Error
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    val storeRepository: StoreRepository
) {

    suspend fun postLogin(username: String, password: String): Result<String> {
        return try {
            val request = LoginRequest(username, password)
            val response = loginRepository.postLogin(request.getRequestBody())
            response.stores.forEachIndexed { index, store ->
                store.localStoreId = (index + 1).toLong()
            }
            storeRepository.insertStores(response.stores)
            Success(response.message)
        } catch (e: Exception) {
            Error(e)
        }
    }

}