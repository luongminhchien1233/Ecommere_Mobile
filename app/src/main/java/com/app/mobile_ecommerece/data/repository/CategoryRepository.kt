package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.CategoryRemoteService
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryRemoteService: CategoryRemoteService) {
    suspend fun getAllCategories() = withContext(Dispatchers.IO) {
        when (val result = categoryRemoteService.getAllCategories()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null || data.isEmpty())
                    throw NetworkErrorException("Category Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun createCategory(createCategoryRequest: CreateCategoryRequest) = withContext(Dispatchers.IO) {
        when (val result = categoryRemoteService.createCategory(createCategoryRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Category Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateCategory(id : String, createCategoryRequest: CreateCategoryRequest) = withContext(Dispatchers.IO) {
        when (val result = categoryRemoteService.updateCategory(id, createCategoryRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Category Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun deleteCategory(id : String) = withContext(Dispatchers.IO) {
        when (val result = categoryRemoteService.deleteCategory(id)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Category Fail")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

}