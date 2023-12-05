package com.app.mobile_ecommerece.data.repository

import android.accounts.NetworkErrorException
import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.data.services.AddressRemoteService
import com.app.mobile_ecommerece.data.services.RoomRemoteService
import com.app.mobile_ecommerece.model.Request.AddressRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRespository @Inject constructor(private val addressRemoteService: AddressRemoteService){
    suspend fun getProvince() = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.getProvince()) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null || data.isEmpty())
                    throw NetworkErrorException("Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getDisctrict(id: Int) = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.getDisctrict(id)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getTown(id: Int) = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.getTown(id)) {
            is NetWorkResult.Success -> {
                val data = result.data
                if (data == null)
                    throw NetworkErrorException("Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun getAddress() = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.getAddress()) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null || data.isEmpty())
                    throw NetworkErrorException("Address Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun addAddress(addressRequest: AddressRequest) = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.addAddress(addressRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Address Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun deleteAddress(id: String) = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.deleteAddress(id)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Address Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun updateAddress(id: String, addressRequest: AddressRequest) = withContext(Dispatchers.IO) {
        when (val result = addressRemoteService.updateAddress(id, addressRequest)) {
            is NetWorkResult.Success -> {
                val data = result.data.data
                if (data == null)
                    throw NetworkErrorException("Address Empty")
                else
                    data
            }
            is NetWorkResult.Error -> {
                throw result.exception
            }
        }
    }
}