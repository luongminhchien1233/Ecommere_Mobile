package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.CartRespository
import com.app.mobile_ecommerece.data.repository.TokenRepository
import com.app.mobile_ecommerece.model.CartData
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.CartRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRespository: CartRespository,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {
    private var _cartData = MutableLiveData<CartData>()
    val cartData: LiveData<CartData> = _cartData
    private var _cartItemData = MutableLiveData<List<CartModel>>()
    val cartItemData: LiveData<List<CartModel>> = _cartItemData
    fun getCart() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val cart = cartRespository.getCart()
            _cartData.postValue(cart)
            _cartItemData.postValue(cart.products)
        }
        registerJobFinish()
    }

    fun updateCart(cartRequest: CartRequest){
        parentJob = viewModelScope.launch(handler) {
            val cart = cartRespository.updateCart(cartRequest)
            _cartData.postValue(cart)
            _cartItemData.postValue(cart.products)
        }
        registerJobFinish()
    }

    fun setEmpty() {
        parentJob = viewModelScope.launch(handler) {
            _cartItemData.postValue(listOf())
        }
        registerJobFinish()
    }

    fun checkIsLogin(): Boolean {
        return tokenRepository.checkIsLogin()
    }
}

