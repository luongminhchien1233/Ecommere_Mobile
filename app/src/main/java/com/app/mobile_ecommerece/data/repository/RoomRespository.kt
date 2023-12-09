package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.RoomRemoteService
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
}