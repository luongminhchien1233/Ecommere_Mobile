package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.UserApi
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.*
import javax.inject.Inject

class UserRemoteService @Inject constructor(private val userApi: UserApi) : BaseRemoteService() {
    suspend fun login(loginRequest: LoginRequest): NetWorkResult<CustomResponse<TokenJson>> =
        handleApi { userApi.login(loginRequest) }

    suspend fun signup(signupRequest: SignupRequest): NetWorkResult<CustomResponse<UserJson>> =
        handleApi { userApi.signup(signupRequest) }

    suspend fun getProfile(): NetWorkResult<CustomResponse<UserJson>> =
        handleApi { userApi.getProfile() }

    suspend fun updateProfile(profileRequest: ProfileRequest): NetWorkResult<CustomResponse<UserJson>> =
        handleApi { userApi.updateProfile(profileRequest) }

    suspend fun changePassword(password: ChangePasswordRequest): NetWorkResult<SimpleRespone> =
        handleApi { userApi.changePassword(password) }

    suspend fun getAllUser(): NetWorkResult<CustomResponse<List<UserAdminDataJson>>> =
        handleApi { userApi.getAllUser() }

    suspend fun adminGetStatistic(): NetWorkResult<CustomResponse<StatisticModel>> =
        handleApi { userApi.adminGetStatistic() }

    suspend fun updateRoleAdmin(role: UpdateRoleRequest, id : String): NetWorkResult<CustomResponse<UserAdminDataJson>> =
        handleApi { userApi.updateRole(role, id) }
}