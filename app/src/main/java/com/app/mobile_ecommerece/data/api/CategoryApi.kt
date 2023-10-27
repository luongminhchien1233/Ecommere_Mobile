package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.CustomResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET(ConstantsURL.CATEGORY_URL)
    suspend fun getAllCategories(): Response<CustomResponse<List<CategoryModel>>>
}