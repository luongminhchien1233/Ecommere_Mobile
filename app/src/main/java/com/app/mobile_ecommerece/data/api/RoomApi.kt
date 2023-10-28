package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.RoomModel
import retrofit2.Response
import retrofit2.http.GET

interface RoomApi {
    @GET(ConstantsURL.ROOM_URL)
    suspend fun getAllRooms(): Response<CustomResponse<List<RoomModel>>>
}