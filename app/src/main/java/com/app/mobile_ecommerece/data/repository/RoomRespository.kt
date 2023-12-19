package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.RoomRemoteService
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.CreateRoomRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRespository @Inject constructor(private val roomRemoteService: RoomRemoteService){
    suspend fun getAllRooms() = withContext(Dispatchers.IO) {
        when (val result = roomRemoteService.getAllRooms()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null || data.isEmpty())
                    throw NetworkErrorException("Room Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getCategoryByRoom(id: String) = withContext(Dispatchers.IO) {
        when (val result = roomRemoteService.getCategoryByRoom(id)) {
            is NetWorkResult.Success -> {
                val data = result.data.data!!.categories
                if (data == null || data.isEmpty())
                    throw NetworkErrorException("Room Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun createRoom(createRoomRequest: CreateRoomRequest) = withContext(Dispatchers.IO) {
        when (val result = roomRemoteService.createRoom(createRoomRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Room Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateRoom(id : String, createRoomRequest: CreateRoomRequest) = withContext(Dispatchers.IO) {
        when (val result = roomRemoteService.updateRoom(id, createRoomRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Room Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun deleteRoom(id : String) = withContext(Dispatchers.IO) {
        when (val result = roomRemoteService.deleteRoom(id)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Category Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }
}