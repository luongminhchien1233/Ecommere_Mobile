package com.app.mobile_ecommerece.data.repository

import android.util.Log
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.UserRemoteService
import com.app.mobile_ecommerece.model.LoginRequest
import com.app.mobile_ecommerece.model.SignupRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteService: UserRemoteService) {
    suspend fun login(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.login(loginRequest)) {
            is NetWorkResult.Success -> {
                result.data.data!!;
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun signup(signupRequest: SignupRequest) = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.signup(signupRequest)) {
            is NetWorkResult.Success -> {
                result.data.data!!;
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }
}