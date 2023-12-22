package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductAdminModel
import com.app.mobile_ecommerece.model.ProductData
import com.app.mobile_ecommerece.model.ProductModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {
    @GET(ConstantsURL.PRODUCT_URL)
    suspend fun getAllProducts(): Response<CustomResponse<ProductData>>

    @GET(ConstantsURL.PRODUCT_BYCATE_URL)
    suspend fun getProductByCategory(@Path("id") id: String): Response<CustomResponse<List<ProductModel>>>

    @GET(ConstantsURL.PRODUCT_BYROOM_URL)
    suspend fun getProductByRoom(@Path("id") id: String): Response<CustomResponse<List<ProductModel>>>

    @GET(ConstantsURL.PRODUCT_ADMIN_URL)
    suspend fun getAllByAdmin(): Response<CustomResponse<List<ProductAdminModel>>>

    @Multipart
    @POST(ConstantsURL.PRODUCT_CREATE_URL)
    suspend fun createProduct(
        @Part images: List<MultipartBody.Part>,
        @Part("code") code: RequestBody,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("shortDescription") shortDescription: RequestBody,
        @Part("category") category: RequestBody,
        @Part("room") room: RequestBody,
        @Part("specs") specs: RequestBody,
        @Part("price") price: Int,
        @Part("quantity") quantity: Int,
    ): Response<CustomResponse<ProductModel>>
}