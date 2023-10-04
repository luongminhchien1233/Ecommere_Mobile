package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.UserApi
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.LoginRequest
import com.app.mobile_ecommerece.model.UserJson
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRemoteService @Inject constructor(private val userApi: UserApi) : BaseRemoteService() {
    suspend fun login(loginRequest: LoginRequest): NetWorkResult<CustomResponse<UserJson>> =
        handleApi { userApi.login(loginRequest) }
}