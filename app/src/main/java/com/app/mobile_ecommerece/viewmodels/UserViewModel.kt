package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.app.e_commerce_app.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.LoginRequest
import com.app.mobile_ecommerece.model.UserJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel() {
    private var _userLiveData = MutableLiveData<UserJson>()
    val userLiveData: LiveData<UserJson> = _userLiveData

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
}