package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.RoomApi
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.CreateRoomRequest
import javax.inject.Inject

class RoomRemoteService @Inject constructor(private val roomApi: RoomApi) : BaseRemoteService() {
    suspend fun getAllRooms(): NetWorkResult<CustomResponse<List<RoomModel>>> =
        handleApi { roomApi.getAllRooms() }

    suspend fun getCategoryByRoom(id: String): NetWorkResult<CustomResponse<RoomData>> =
        handleApi { roomApi.getCategoryByRoom(id) }

    suspend fun createRoom(createRoomRequest: CreateRoomRequest): NetWorkResult<CustomResponse<CreateRoomData>> =
        handleApi { roomApi.createRoom(createRoomRequest) }

    suspend fun updateRoom(id : String,createRoomRequest: CreateRoomRequest): NetWorkResult<CustomResponse<CreateRoomData>> =
        handleApi { roomApi.updaateRoom(id, createRoomRequest) }

    suspend fun deleteRoom(id : String): NetWorkResult<SimpleRespone> =
        handleApi { roomApi.deleteRoom(id) }
}