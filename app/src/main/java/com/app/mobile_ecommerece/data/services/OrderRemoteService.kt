package com.app.mobile_ecommerece.data.services


import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.OrderApi
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import com.app.mobile_ecommerece.model.SimpleRespone
import retrofit2.Response
import javax.inject.Inject

class OrderRemoteService @Inject constructor(private val orderApi: OrderApi) : BaseRemoteService(){
    suspend fun getUserOrder(): NetWorkResult<CustomResponse<List<OrderData>>> =
        handleApi { orderApi.getUserOrder() }

    suspend fun createOrder(orderRequest: CreateOrderRequest): NetWorkResult<SimpleRespone> =
        handleApi { orderApi.createOrder(orderRequest) }

}