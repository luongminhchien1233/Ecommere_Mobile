package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.OrderRespository
import com.app.mobile_ecommerece.model.CartData
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.Request.CartRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRespository: OrderRespository
) : BaseViewModel() {
    private var _orderData = MutableLiveData<List<OrderData>>()
    val orderData: LiveData<List<OrderData>> = _orderData

    private var _activeOrderData = MutableLiveData<OrderData>()
    val activeOrderData: LiveData<OrderData> = _activeOrderData
    fun getUserOrder() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = orderRespository.getUserOrder()
            _orderData.postValue(data)
        }
        registerJobFinish()
    }

}