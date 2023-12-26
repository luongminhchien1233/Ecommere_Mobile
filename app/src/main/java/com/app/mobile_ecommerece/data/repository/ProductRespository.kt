package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.ProductRemoteService
import com.app.mobile_ecommerece.data.services.UserRemoteService
import com.app.mobile_ecommerece.model.Request.ProductEnableRequest
import com.app.mobile_ecommerece.model.Request.ProductUpdateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ProductRespository @Inject constructor(private val productRemoteService: ProductRemoteService){
    suspend fun getAllProducts() = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.getAllProducts()) {
            is NetWorkResult.Success -> {
                val data = result.data.data!!.product
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

    suspend fun enableProduct(enableRequest: ProductEnableRequest, id: String) = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.enableProduct(enableRequest, id)) {
            is NetWorkResult.Success -> {
                result.data!!
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateProduct(updateRequest: ProductUpdateRequest, id: String) = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.updateProduct(updateRequest, id)) {
            is NetWorkResult.Success -> {
                result.data!!
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getAllByAdmin() = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.getAllByAdmin()) {
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

    suspend fun createProduct(
        images: List<MultipartBody.Part>,
        code: RequestBody,
        name: RequestBody,
        description: RequestBody,
        shortDescription: RequestBody,
        category: RequestBody,
        room: RequestBody,
        specs: RequestBody,
        price: Int,
        quantity: Int
    ) = withContext(Dispatchers.IO) {
        when (val result = productRemoteService.createProduct(images, code, name, description, shortDescription, category
        , room, specs, price, quantity
        )) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data.status != "success")
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