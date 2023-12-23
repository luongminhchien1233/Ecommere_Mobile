package com.app.mobile_ecommerece.data.services


import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.OrderApi
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import retrofit2.Response
import javax.inject.Inject

class OrderRemoteService @Inject constructor(private val orderApi: OrderApi) : BaseRemoteService(){
    suspend fun getUserOrder(): NetWorkResult<CustomResponse<List<OrderUserData>>> =
        handleApi { orderApi.getUserOrder() }

    suspend fun createOrder(orderRequest: CreateOrderRequest): NetWorkResult<SimpleRespone> =
        handleApi { orderApi.createOrder(orderRequest) }

    suspend fun getAllUserOrder(): NetWorkResult<CustomResponse<List<OrderData>>> =
        handleApi { orderApi.getAllUserOrder() }

    suspend fun updateOrderAdmin(orderRequest: OrderRequest, id : String): NetWorkResult<SimpleRespone> =
        handleApi { orderApi.updateOrderStatus(orderRequest, id) }

}