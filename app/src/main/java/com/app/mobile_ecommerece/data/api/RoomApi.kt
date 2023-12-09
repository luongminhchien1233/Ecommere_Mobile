package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.RoomData
import com.app.mobile_ecommerece.model.RoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApi {
    @GET(ConstantsURL.ROOM_URL)
    suspend fun getAllRooms(): Response<CustomResponse<List<RoomModel>>>

    @GET(ConstantsURL.ROOM_CATEGORY_URL)
    suspend fun getCategoryByRoom(@Path("id") id: String): Response<CustomResponse<RoomData>>
}