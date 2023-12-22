package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.api.ProductApi
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.ProductAdminModel
import com.app.mobile_ecommerece.model.ProductData
import com.app.mobile_ecommerece.model.ProductModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ProductRemoteService @Inject constructor(private val productApi: ProductApi) : BaseRemoteService() {
    suspend fun getAllProducts(): NetWorkResult<CustomResponse<ProductData>> =
        handleApi { productApi.getAllProducts() }
    suspend fun getProductByCategory(id: String): NetWorkResult<CustomResponse<List<ProductModel>>> =
        handleApi { productApi.getProductByCategory(id) }

    suspend fun getProductByRoom(id: String): NetWorkResult<CustomResponse<List<ProductModel>>> =
        handleApi { productApi.getProductByRoom(id) }

    suspend fun getAllByAdmin(): NetWorkResult<CustomResponse<List<ProductAdminModel>>> =
        handleApi { productApi.getAllByAdmin() }

    suspend fun createProduct(
        images: List<MultipartBody.Part>,
        code: RequestBody,
        name: RequestBody,
        description: RequestBody,
        shortDescription: RequestBody,
        category: RequestBody,
        room: RequestBody,
        specs: RequestBody,
        price: Int,
        quantity: Int
    ): NetWorkResult<CustomResponse<ProductModel>> =
        handleApi {
            productApi.createProduct(
                images = images,
                code = code,
                name = name,
                description = description,
                shortDescription = shortDescription,
                category = category,
                room = room,
                specs = specs,
                price = price,
                quantity = quantity
            )
        }
}