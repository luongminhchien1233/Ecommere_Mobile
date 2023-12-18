package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.CategoryApi
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.UserApi
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import javax.inject.Inject

class CategoryRemoteService @Inject constructor(private val categoryApi: CategoryApi) : BaseRemoteService() {
    suspend fun getAllCategories(): NetWorkResult<CustomResponse<List<CategoryModel>>> =
        handleApi { categoryApi.getAllCategories() }

    suspend fun createCategory(createCategoryRequest: CreateCategoryRequest): NetWorkResult<CustomResponse<CreateCategoryData>> =
        handleApi { categoryApi.createCategory(createCategoryRequest) }

    suspend fun updateCategory(id : String,createCategoryRequest: CreateCategoryRequest): NetWorkResult<CustomResponse<CreateCategoryData>> =
        handleApi { categoryApi.updateCategory(id, createCategoryRequest) }

    suspend fun deleteCategory(id : String): NetWorkResult<SimpleRespone> =
        handleApi { categoryApi.deleteCategory(id) }
}