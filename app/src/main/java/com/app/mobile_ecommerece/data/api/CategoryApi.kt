package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CartData
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.CreateCategoryData
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApi {
    @GET(ConstantsURL.CATEGORY_URL)
    suspend fun getAllCategories(): Response<CustomResponse<List<CategoryModel>>>

    @POST(ConstantsURL.CATEGORY_ADD_URL)
    suspend fun createCategory(@Body createCategoryRequest: CreateCategoryRequest): Response<CustomResponse<CreateCategoryData>>

}