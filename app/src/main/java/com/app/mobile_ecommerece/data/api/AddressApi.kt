package com.app.mobile_ecommerece.data.api

import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.model.ThirdParties.DisctrictModel
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import com.app.mobile_ecommerece.model.ThirdParties.TownModel
import retrofit2.Response
import retrofit2.http.*

interface AddressApi {
    @GET
    suspend fun getProvince(@Url url: String): Response<List<ProvinceModel>>
    
    @GET(ConstantsURL.DISCTRICT_URL)
    suspend fun getDisctrict(@Path("id") id: Int): Response<ProvinceModel>

    @GET(ConstantsURL.TOWN_URL)
    suspend fun getTown(@Path("id") id: Int): Response<DisctrictModel>

    @POST(ConstantsURL.ADDRESS_URL)
    suspend fun addAddress(@Body addressRequest: AddressRequest): Response<CustomResponse<List<AddressModel>>>

    @GET(ConstantsURL.ADDRESS_URL)
    suspend fun getAddress(): Response<CustomResponse<List<AddressModel>>>

    @DELETE(ConstantsURL.ADDRESS_EDIT_URL)
    suspend fun deleteAddress(@Path("id") id: String): Response<CustomResponse<List<AddressModel>>>

    @PUT(ConstantsURL.ADDRESS_EDIT_URL)
    suspend fun updateAddress(@Path("id") id: String, @Body addressRequest: AddressRequest): Response<CustomResponse<List<AddressModel>>>
}