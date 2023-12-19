package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.CreateRoomRequest
import retrofit2.Response
import retrofit2.http.*

interface RoomApi {
    @GET(ConstantsURL.ROOM_URL)
    suspend fun getAllRooms(): Response<CustomResponse<List<RoomModel>>>

    @GET(ConstantsURL.ROOM_CATEGORY_URL)
    suspend fun getCategoryByRoom(@Path("id") id: String): Response<CustomResponse<RoomData>>

    @POST(ConstantsURL.ROOM_ADD_URL)
    suspend fun createRoom(@Body createRoomRequest: CreateRoomRequest): Response<CustomResponse<CreateRoomData>>

    @PUT(ConstantsURL.ROOM_EDIT_URL)
    suspend fun updaateRoom(@Path("id") id: String, @Body createRoomRequest: CreateRoomRequest): Response<CustomResponse<CreateRoomData>>

    @DELETE(ConstantsURL.ROOM_EDIT_URL)
    suspend fun deleteRoom(@Path("id") id: String): Response<SimpleRespone>
}