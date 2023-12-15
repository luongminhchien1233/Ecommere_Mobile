package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CartData
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import com.app.mobile_ecommerece.model.SimpleRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST(ConstantsURL.ORDER_URL)
    suspend fun createOrder(@Body orderRequest: CreateOrderRequest): Response<SimpleRespone>

    @GET(ConstantsURL.ORDER_GET_URL)
    suspend fun getUserOrder(): Response<CustomResponse<List<OrderData>>>

    @GET(ConstantsURL.ORDER_GETALL_URL)
    suspend fun getAllUserOrder(): Response<CustomResponse<List<OrderData>>>

}