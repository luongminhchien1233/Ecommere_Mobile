package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.ProductRemoteService
import com.app.mobile_ecommerece.data.services.UserRemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRespository @Inject constructor(private val productRemoteService: ProductRemoteService){
    suspend fun getAllProducts() = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.getAllProducts()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Product Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getProductByCategory(id: String) = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.getProductByCategory(id)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Product Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getProductByRoom(id: String) = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.getProductByRoom(id)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Product Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }
}