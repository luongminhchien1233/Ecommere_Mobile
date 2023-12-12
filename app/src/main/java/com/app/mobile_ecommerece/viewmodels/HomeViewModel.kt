package com.app.mobile_ecommerece.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.common.Event
import com.app.mobile_ecommerece.data.repository.*
import com.app.mobile_ecommerece.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomRespository: RoomRespository,
    private val productRespository: ProductRespository,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    private var _productsData = MutableLiveData<List<ProductModel>>()
    val productsData: LiveData<List<ProductModel>> = _productsData

    private var _roomsData = MutableLiveData<List<RoomModel>>()
    val roomsData: LiveData<List<RoomModel>> = _roomsData

    var isProductsLoading = MutableLiveData<Event<Boolean>>()

    var isFetchDataSuccess = MutableLiveData<Event<Boolean>>()

    fun fetchData() {
        showLoading(true)
        isFetchDataSuccess.postValue(Event(false))
        parentJob = viewModelScope.launch(handler) {
            delay(1000)

            val roomsDeferred = async { roomRespository.getAllRooms() }
            val productsDeferred = async { productRespository.getAllProducts() }
//            val userDeferred = async { userRepository.getUserProfile() }
//
//            _userLiveData.postValue(userDeferred.await())

            val fetchedRooms = roomsDeferred.await()
            val fetchedProducts = productsDeferred.await()
            _roomsData.postValue(fetchedRooms)

//            _categoryRadioData.postValue(toListCategoryRadioButton(fetchedCategories))
            _productsData.postValue(fetchedProducts)
            isFetchDataSuccess.postValue(Event(true))
        }
        registerJobFinish()
    }
    fun checkIsLogin(): Boolean {
        return tokenRepository.checkIsLogin()
    }

    fun logout() {
        tokenRepository.removeToken()
    }
}