package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.LoginRequest
import com.app.mobile_ecommerece.model.Request.ProfileRequest
import com.app.mobile_ecommerece.model.Request.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {
    @POST(ConstantsURL.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<CustomResponse<TokenJson>>

    @POST(ConstantsURL.REGISTER_URL)
    suspend fun signup(@Body signupRequest: SignupRequest): Response<CustomResponse<UserJson>>

    @GET(ConstantsURL.PROFILE_URL)
    suspend fun getProfile(): Response<CustomResponse<UserJson>>

    @PUT(ConstantsURL.PROFILE_UPDATE_URL)
    suspend fun updateProfile(@Body profileRequest: ProfileRequest): Response<CustomResponse<UserJson>>
}