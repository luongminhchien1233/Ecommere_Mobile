package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import com.app.mobile_ecommerece.model.Request.ProfileRequest
import retrofit2.Response
import retrofit2.http.*

interface OrderApi {
    @POST(ConstantsURL.ORDER_URL)
    suspend fun createOrder(@Body orderRequest: CreateOrderRequest): Response<SimpleRespone>

    @GET(ConstantsURL.ORDER_GET_URL)
    suspend fun getUserOrder(): Response<CustomResponse<List<OrderData>>>

    @GET(ConstantsURL.ORDER_GETALL_URL)
    suspend fun getAllUserOrder(): Response<CustomResponse<List<OrderData>>>

    @PUT(ConstantsURL.ORDER_UPDATE_ADMIN_URL)
    suspend fun updateOrderStatus(@Body orderRequest: OrderRequest, @Path("id") id: String): Response<SimpleRespone>

}