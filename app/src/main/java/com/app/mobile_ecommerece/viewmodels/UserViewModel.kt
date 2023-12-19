package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.TokenRepository
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.Request.LoginRequest
import com.app.mobile_ecommerece.model.Request.ProfileRequest
import com.app.mobile_ecommerece.model.Request.SignupRequest
import com.app.mobile_ecommerece.model.TokenJson
import com.app.mobile_ecommerece.model.UserJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {
    private var _userLiveData = MutableLiveData<TokenJson>()
    val userLiveData: LiveData<TokenJson> = _userLiveData
    private var _userInfoLiveData = MutableLiveData<UserJson>()
    val userInfoLiveData: LiveData<UserJson> = _userInfoLiveData

    fun login(loginRequest: LoginRequest, remember: Boolean) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val user = userRepository.login(loginRequest)
            _userLiveData.postValue(user)
            tokenRepository.removeToken()
            tokenRepository.saveToken(user.toTokenModel())
            tokenRepository.setRemember(remember)
            tokenRepository.setRole(user.role)
            navigateToPage(R.id.action_loginFragment_to_homeFragment)
        }
        registerJobFinish()
    }

    fun isAdmin(): Boolean? {
        if(tokenRepository.getRole() == "admin"){
            return true
        }
        else{
            return false
        }
    }

    fun getRole(): String{
        return tokenRepository.getRole().toString()
    }

    fun logout() {
        tokenRepository.removeToken()
    }

    fun setRemember(remember: Boolean) {
        tokenRepository.setRemember(remember)
    }

    fun getRemember(): Boolean? {
        return tokenRepository.getRemember()
    }

    fun signup(signupRequest: SignupRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val user = userRepository.signup(signupRequest)
            navigateToPage(R.id.action_signupFragment_to_loginFragment)
        }
        registerJobFinish()
    }

    fun checkIsLogin(): Boolean {
        return tokenRepository.checkIsLogin()
    }

    fun getProfile() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val profile = userRepository.getProfile()
            _userInfoLiveData.postValue(profile)
        }
        registerJobFinish()
    }

    fun updateProfile(profileRequest: ProfileRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val profile = userRepository.updateProfile(profileRequest)
            _userInfoLiveData.postValue(profile)
        }
        registerJobFinish()
    }
}