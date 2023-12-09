package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductData
import com.app.mobile_ecommerece.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET(ConstantsURL.PRODUCT_URL)
    suspend fun getAllProducts(): Response<CustomResponse<ProductData>>

    @GET(ConstantsURL.PRODUCT_BYCATE_URL)
    suspend fun getProductByCategory(@Path("id") id: String): Response<CustomResponse<List<ProductModel>>>

    @GET(ConstantsURL.PRODUCT_BYROOM_URL)
    suspend fun getProductByRoom(@Path("id") id: String): Response<CustomResponse<List<ProductModel>>>
}