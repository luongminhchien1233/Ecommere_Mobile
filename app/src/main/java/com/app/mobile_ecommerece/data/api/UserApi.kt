package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import dagger.Binds
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @POST(ConstantsURL.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<CustomResponse<UserJson>>
}