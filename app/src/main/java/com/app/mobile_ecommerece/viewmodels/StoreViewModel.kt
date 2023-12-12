package com.app.mobile_ecommerece.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.common.Event
import com.app.mobile_ecommerece.data.repository.CategoryRepository
import com.app.mobile_ecommerece.data.repository.ProductRespository
import com.app.mobile_ecommerece.data.repository.RoomRespository
import com.app.mobile_ecommerece.data.repository.TokenRepository
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.RoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val productRespository: ProductRespository,
    private val categoryRepository: CategoryRepository,
    private val roomRespository: RoomRespository,
) : BaseViewModel() {

    private var _productsData = MutableLiveData<List<ProductModel>>()
    val productsData: LiveData<List<ProductModel>> = _productsData

    private var _categoriesData = MutableLiveData<List<CategoryModel>>()
    val categoriesData: LiveData<List<CategoryModel>> = _categoriesData

    private var _roomsData = MutableLiveData<List<RoomModel>>()
    val roomsData: LiveData<List<RoomModel>> = _roomsData

    var isFetchDataProductEmpty = MutableLiveData<Event<Boolean>>()



    fun getALlProduct() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedProducts = productRespository.getAllProducts()
            _productsData.postValue(fetchedProducts)
        }
        registerJobFinish()
    }

    fun getALlRoom() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedRoom = roomRespository.getAllRooms()
            _roomsData.postValue(fetchedRoom)
        }
        registerJobFinish()
    }

    fun getProductByCategory(id: String) {
        parentJob = viewModelScope.launch(handler) {
            val data = productRespository.getProductByCategory(id)
                _productsData.postValue(data)
        }
        registerJobFinish()
    }

    fun getProductByRoom(id: String) {
        parentJob = viewModelScope.launch(handler) {
            val data = productRespository.getProductByRoom(id)
            _productsData.postValue(data)
        }
        registerJobFinish()
    }

    fun getALlCategories() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedCategories = categoryRepository.getAllCategories()
            _categoriesData.postValue(fetchedCategories)
        }
        registerJobFinish()
    }

    fun getCategoryByRoom(id: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedCategories = roomRespository.getCategoryByRoom(id)
            _categoriesData.postValue(fetchedCategories)
        }
        registerJobFinish()
    }
}