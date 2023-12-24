package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.UserRemoteService
import com.app.mobile_ecommerece.model.Request.LoginRequest
import com.app.mobile_ecommerece.model.Request.ProfileRequest
import com.app.mobile_ecommerece.model.Request.SignupRequest
import com.app.mobile_ecommerece.model.Request.UpdateRoleRequest
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

    suspend fun getProfile() = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.getProfile()) {
            is NetWorkResult.Success -> {
                result.data.data!!;
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun adminGetStatistic() = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.adminGetStatistic()) {
            is NetWorkResult.Success -> {
                result.data.data!!
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateProfile(profileRequest: ProfileRequest) = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.updateProfile(profileRequest)) {
            is NetWorkResult.Success -> {
                result.data.data!!;
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getAllUser() = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.getAllUser()) {
            is NetWorkResult.Success -> {
                result.data.data!!;
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateRole(role: UpdateRoleRequest, id: String) = withContext(Dispatchers.IO) {
        when (val result = userRemoteService.updateRoleAdmin(role, id)) {
            is NetWorkResult.Success -> {
                val data = result.data;
                if (data.status != "success")
                    throw NetworkErrorException("Update Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }
}