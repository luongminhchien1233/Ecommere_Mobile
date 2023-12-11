package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.CategoryRemoteService
import com.app.mobile_ecommerece.data.services.OrderRemoteService
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

}