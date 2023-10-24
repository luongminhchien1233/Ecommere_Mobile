package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST(ConstantsURL.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<CustomResponse<TokenJson>>

    @POST(ConstantsURL.REGISTER_URL)
    suspend fun signup(@Body signupRequest: SignupRequest): Response<CustomResponse<UserJson>>
}