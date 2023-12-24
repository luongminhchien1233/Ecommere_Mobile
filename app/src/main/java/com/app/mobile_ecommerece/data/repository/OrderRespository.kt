package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.CategoryRemoteService
import com.app.mobile_ecommerece.data.services.OrderRemoteService
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRespository @Inject constructor(private val orderRemoteService: OrderRemoteService) {
    suspend fun getUserOrder() = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.getUserOrder()) {
            is NetWorkResult.Success -> {
                result.data.data!!
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getAllUserOrder() = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.getAllUserOrder()) {
            is NetWorkResult.Success -> {
                result.data.data!!
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

    suspend fun updateOrder(orderRequest: OrderRequest, id: String) = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.updateOrderAdmin(orderRequest, id)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Address Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun cancelOrder(orderRequest: OrderRequest, id: String) = withContext(Dispatchers.IO) {
        when (val result = orderRemoteService.cancelOrder(orderRequest, id)) {
            is NetWorkResult.Success -> {
                result.data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

}