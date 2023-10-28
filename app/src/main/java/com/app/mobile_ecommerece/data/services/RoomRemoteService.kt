package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.RoomApi
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.RoomModel
import javax.inject.Inject

class RoomRemoteService @Inject constructor(private val roomApi: RoomApi) : BaseRemoteService() {
    suspend fun getAllRooms(): NetWorkResult<CustomResponse<List<RoomModel>>> =
        handleApi { roomApi.getAllRooms() }
}