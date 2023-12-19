package com.app.mobile_ecommerece.data.api

import androidx.room.Update
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.*
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @POST(ConstantsURL.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<CustomResponse<TokenJson>>

    @POST(ConstantsURL.REGISTER_URL)
    suspend fun signup(@Body signupRequest: SignupRequest): Response<CustomResponse<UserJson>>

    @GET(ConstantsURL.PROFILE_URL)
    suspend fun getProfile(): Response<CustomResponse<UserJson>>

    @PUT(ConstantsURL.PROFILE_UPDATE_URL)
    suspend fun updateProfile(@Body profileRequest: ProfileRequest): Response<CustomResponse<UserJson>>

    @GET(ConstantsURL.GET_ALL_USER)
    suspend fun getAllUser(): Response<CustomResponse<List<UserAdminDataJson>>>

    @PUT(ConstantsURL.UPDATE_ROLE_URL)
    suspend fun updateRole(@Body role: UpdateRoleRequest, @Path("id") id: String): Response<CustomResponse<UserAdminDataJson>>
}