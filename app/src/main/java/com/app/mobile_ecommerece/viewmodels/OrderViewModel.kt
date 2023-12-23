package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.AddressRespository
import com.app.mobile_ecommerece.data.repository.CartRespository
import com.app.mobile_ecommerece.data.repository.OrderRespository
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.model.Request.CreateOrderRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRespository: OrderRespository,
    private val cartRespository: CartRespository,
    private val userRespository: UserRepository,
    private val addressRespository: AddressRespository
) : BaseViewModel() {
    private var _orderData = MutableLiveData<List<OrderUserData>>()
    val orderData: LiveData<List<OrderUserData>> = _orderData

    private var _activeOrderData = MutableLiveData<OrderData>()
    val activeOrderData: LiveData<OrderData> = _activeOrderData

    private var _cartItemData = MutableLiveData<List<CartModel>>()
    val cartItemData: LiveData<List<CartModel>> = _cartItemData

    private var _totalPriceData = MutableLiveData<Int>()
    val totalPriceData: LiveData<Int> = _totalPriceData

    private var _userInfoLiveData = MutableLiveData<UserJson>()
    val userInfoLiveData: LiveData<UserJson> = _userInfoLiveData

    private var _addressChooseData = MutableLiveData<OrderAddressModel>()
    val addressChooseData: LiveData<OrderAddressModel> = _addressChooseData
    fun getUserOrder() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = orderRespository.getUserOrder()
            _orderData.postValue(data)
        }
        registerJobFinish()
    }

    fun getProductInCart() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val cart = cartRespository.getCart()
            _cartItemData.postValue(cart!!.products)
            _totalPriceData.postValue(cart.cartTotal)
        }
        registerJobFinish()
    }


    fun getProfile() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val profile = userRespository.getProfile()
            _userInfoLiveData.postValue(profile)
        }
        registerJobFinish()
    }

    fun upLoadSelectAddress(address: OrderAddressModel){
        _addressChooseData.postValue(address)
    }

    fun getAllAddress() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = addressRespository.getAddress()
            for (addressModel in data) {
                if (addressModel.default == true) {
                    _addressChooseData.postValue(addressModel.toAddressOrderModel())
                }
            }
        }
        registerJobFinish()
    }

    fun createOrder(orderRequest: CreateOrderRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val status = orderRespository.createOrder(orderRequest)
            if(status!!.status == "success"){
                navigateToPage(R.id.action_checkoutFragment_to_orderSuccessFragment)
            }
        }
        registerJobFinish()
    }
}