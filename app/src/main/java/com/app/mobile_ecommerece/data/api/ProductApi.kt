package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET(ConstantsURL.PRODUCT_URL)
    suspend fun getAllProducts(): Response<CustomResponse<List<ProductModel>>>
}