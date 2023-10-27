package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.CategoryApi
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.UserApi
import com.app.mobile_ecommerece.model.*
import javax.inject.Inject

class CategoryRemoteService @Inject constructor(private val categoryApi: CategoryApi) : BaseRemoteService() {
    suspend fun getAllCategories(): NetWorkResult<CustomResponse<List<CategoryModel>>> =
        handleApi { categoryApi.getAllCategories() }
}