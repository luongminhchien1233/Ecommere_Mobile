package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.CartRemoteService
import com.app.mobile_ecommerece.model.Request.CartRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRespository @Inject constructor(private val cartRemoteService: CartRemoteService){
    suspend fun getCart() = withContext(Dispatchers.IO) {
        when (val result = cartRemoteService.getCart()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null){
                    throw NetworkErrorException("Cart Empty")
                }
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun clearCart() = withContext(Dispatchers.IO) {
        when (val result = cartRemoteService.clearCart()) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null || data.status != "success"){
                    throw NetworkErrorException("Fail")
                }
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun addToCart(cartRequest: CartRequest) = withContext(Dispatchers.IO) {
        when (val result = cartRemoteService.addtoCart(cartRequest)) {
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

    suspend fun updateCart(cartRequest: CartRequest) = withContext(Dispatchers.IO) {
        when (val result = cartRemoteService.updateCart(cartRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null){
                    throw NetworkErrorException("Cart Empty")
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