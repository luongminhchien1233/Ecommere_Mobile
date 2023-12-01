package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.CartApi
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CartRequest
import javax.inject.Inject

class CartRemoteService @Inject constructor(private val cartApi: CartApi) : BaseRemoteService(){
    suspend fun addtoCart(cartRequest: CartRequest): NetWorkResult<SimpleRespone> =
        handleApi { cartApi.addtoCart(cartRequest) }

    suspend fun getCart(): NetWorkResult<CustomResponse<CartData>> =
        handleApi { cartApi.getCart() }

    suspend fun clearCart(): NetWorkResult<SimpleRespone> =
        handleApi { cartApi.clearCart() }

    suspend fun updateCart(cartRequest: CartRequest): NetWorkResult<CustomResponse<CartData>> =
        handleApi { cartApi.updateCart(cartRequest) }
}