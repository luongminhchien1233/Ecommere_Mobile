package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.CartRespository
import com.app.mobile_ecommerece.data.repository.TokenRepository
import com.app.mobile_ecommerece.model.Request.CartRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val cartRespository: CartRespository,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    fun addtoCart(cartRequest: CartRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val status = cartRespository.addToCart(cartRequest)
            if(status!!.status != "success"){
                navigateToPage(R.id.action_productDetailFragment_to_loginFragment);
            }
        }
        registerJobFinish()
    }
    fun checkIsLogin(): Boolean {
        return tokenRepository.checkIsLogin()
    }
}