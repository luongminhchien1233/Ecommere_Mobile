package com.app.mobile_ecommerece.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.AddressRespository
import com.app.mobile_ecommerece.data.repository.CartRespository
import com.app.mobile_ecommerece.data.repository.TokenRepository
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.model.ThirdParties.DisctrictModel
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import com.app.mobile_ecommerece.model.ThirdParties.TownModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRespository: AddressRespository
) : BaseViewModel() {

    private var _addressesData = MutableLiveData<List<AddressModel>>()
    val addressesData: LiveData<List<AddressModel>> = _addressesData

    private var _provincesData = MutableLiveData<List<ProvinceModel>>()
    val provincesData: LiveData<List<ProvinceModel>> = _provincesData
    private var _districtsData = MutableLiveData<List<DisctrictModel>>()
    val districtsData: LiveData<List<DisctrictModel>> = _districtsData
    private var _townsData = MutableLiveData<List<TownModel>>()
    val townsData: LiveData<List<TownModel>> = _townsData

    fun getProvince() {
        parentJob = viewModelScope.launch(handler) {
            val province = addressRespository.getProvince()
            _provincesData.postValue(province)
        }
        registerJobFinish()
    }

    fun getDisctrict(id: Int) {
        parentJob = viewModelScope.launch(handler) {
            val data = addressRespository.getDisctrict(id)
            _districtsData.postValue(data.districts)
        }
        registerJobFinish()
    }

    fun getTown(id: Int) {
        parentJob = viewModelScope.launch(handler) {
            val data = addressRespository.getTown(id)
            _townsData.postValue(data.wards)
        }
        registerJobFinish()
    }

    fun addAddress(addressRequest: AddressRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = addressRespository.addAddress(addressRequest)
            getAllAddress()
            navigateToPage(R.id.action_addAddressFragment_to_addressListFragment)
        }
        registerJobFinish()
    }

    fun getAllAddress() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = addressRespository.getAddress()
            _addressesData.postValue(data)
        }
        registerJobFinish()
    }
}