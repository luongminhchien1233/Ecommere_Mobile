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
                if (data == null || data.isEmpty())
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