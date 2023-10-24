package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.UserApi
import com.app.mobile_ecommerece.model.*
import javax.inject.Inject

class UserRemoteService @Inject constructor(private val userApi: UserApi) : BaseRemoteService() {
    suspend fun login(loginRequest: LoginRequest): NetWorkResult<CustomResponse<TokenJson>> =
        handleApi { userApi.login(loginRequest) }

    suspend fun signup(signupRequest: SignupRequest): NetWorkResult<CustomResponse<UserJson>> =
        handleApi { userApi.signup(signupRequest) }
}