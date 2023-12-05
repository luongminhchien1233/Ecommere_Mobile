package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.ProductApi
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductModel
import javax.inject.Inject

class ProductRemoteService @Inject constructor(private val productApi: ProductApi) : BaseRemoteService() {
    suspend fun getAllProducts(): NetWorkResult<CustomResponse<List<ProductModel>>> =
        handleApi { productApi.getAllProducts() }
}