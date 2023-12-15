package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.CategoryRemoteService
import com.app.mobile_ecommerece.data.services.OrderRemoteService
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRespository @Inject constructor(private val orderRemoteService: OrderRemoteService) {
    suspend fun getUserOrder() = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.getUserOrder()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Order Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getAllUserOrder() = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.getAllUserOrder()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Order Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun createOrder(orderRequest: CreateOrderRequest) = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.createOrder(orderRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null || data.status != "success"){
                    throw NetworkErrorException("Add Fail")
                }
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

}