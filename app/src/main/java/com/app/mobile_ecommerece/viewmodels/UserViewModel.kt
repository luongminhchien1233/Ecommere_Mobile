package com.app.mobile_ecommerece.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.LoginRequest
import com.app.mobile_ecommerece.model.SignupRequest
import com.app.mobile_ecommerece.model.TokenJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel() {
    private var _userLiveData = MutableLiveData<TokenJson>()
    val userLiveData: LiveData<TokenJson> = _userLiveData

//    fun register(registerRequest: RegisterRequest) {
//        showLoading(true)
//        parentJob = viewModelScope.launch(handler) {
//            val res = userRepository.register(registerRequest)
//            navigateToPage(R.id.action_fillProfileFragment_to_loginFragment)
//        }
//        registerJobFinish()
//    }

    fun login(loginRequest: LoginRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val user = userRepository.login(loginRequest)
            _userLiveData.postValue(user)
//            navigateToPage(R.id.action_loginFragment_to_splashFragment)
        }
        registerJobFinish()
    }

    fun signup(signupRequest: SignupRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val user = userRepository.signup(signupRequest)
            navigateToPage(R.id.action_signupFragment_to_loginFragment)
        }
        registerJobFinish()
    }
}