package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CartRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CartApi {
    @POST(ConstantsURL.CART_ADD_URL)
    suspend fun addtoCart(@Body cartRequest: CartRequest): Response<SimpleRespone>

    @GET(ConstantsURL.CART_URL)
    suspend fun getCart(): Response<CustomResponse<CartData>>

    @DELETE(ConstantsURL.CART_EMPTY_URL)
    suspend fun clearCart(): Response<SimpleRespone>

    @PUT(ConstantsURL.CART_UPDATE_URL)
    suspend fun updateCart(@Body cartRequest: CartRequest): Response<CustomResponse<CartData>>
}