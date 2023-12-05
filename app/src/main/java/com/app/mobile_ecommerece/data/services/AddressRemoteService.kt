package com.app.mobile_ecommerece.data.services

import com.app.mobile_ecommerece.base.network.BaseRemoteService
import com.app.mobile_ecommerece.data.api.AddressApi
import com.app.mobile_ecommerece.data.api.ConstantsURL
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.CustomResponse
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.ThirdParties.DisctrictModel
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import javax.inject.Inject

class AddressRemoteService @Inject constructor(private val addressApi: AddressApi) : BaseRemoteService(){


    suspend fun getProvince(): NetWorkResult<List<ProvinceModel>> =
        handleApi { addressApi.getProvince(ConstantsURL.PROVINCE_URL) }

    suspend fun getDisctrict(id: Int): NetWorkResult<ProvinceModel> =
        handleApi { addressApi.getDisctrict(id) }

    suspend fun getTown(id: Int): NetWorkResult<DisctrictModel> =
        handleApi { addressApi.getTown(id) }

    suspend fun addAddress(addressRequest: AddressRequest): NetWorkResult<CustomResponse<List<AddressModel>>> =
        handleApi { addressApi.addAddress(addressRequest) }

    suspend fun getAddress(): NetWorkResult<CustomResponse<List<AddressModel>>> =
        handleApi { addressApi.getAddress() }

}