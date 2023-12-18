package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import retrofit2.Response
import retrofit2.http.*

interface CategoryApi {
    @GET(ConstantsURL.CATEGORY_URL)
    suspend fun getAllCategories(): Response<CustomResponse<List<CategoryModel>>>

    @POST(ConstantsURL.CATEGORY_ADD_URL)
    suspend fun createCategory(@Body createCategoryRequest: CreateCategoryRequest): Response<CustomResponse<CreateCategoryData>>

    @PUT(ConstantsURL.CATEGORY_EDIT_URL)
    suspend fun updateCategory(@Path("id") id: String, @Body createCategoryRequest: CreateCategoryRequest): Response<CustomResponse<CreateCategoryData>>
    @DELETE(ConstantsURL.CATEGORY_EDIT_URL)
    suspend fun deleteCategory(@Path("id") id: String): Response<SimpleRespone>

}