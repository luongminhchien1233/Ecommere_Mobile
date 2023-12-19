package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.CategoryRepository
import com.app.mobile_ecommerece.data.repository.OrderRespository
import com.app.mobile_ecommerece.data.repository.RoomRespository
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.CreateRoomRequest
import com.app.mobile_ecommerece.model.Request.OrderRequest
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.model.UserAdminDataJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val categoryRespository: CategoryRepository,
    private val roomRespository: RoomRespository,
    private val userRespository: UserRepository,
    private val orderRespository: OrderRespository
) : BaseViewModel() {

    private var _categoriesData = MutableLiveData<List<CategoryModel>>()
    val categoriesData: LiveData<List<CategoryModel>> = _categoriesData

    private var _roomsData = MutableLiveData<List<RoomModel>>()
    val roomsData: LiveData<List<RoomModel>> = _roomsData

    private var _usersData = MutableLiveData<List<UserAdminDataJson>>()
    val usersData: LiveData<List<UserAdminDataJson>> = _usersData

    private var _ordersData = MutableLiveData<List<OrderData>>()
    val ordersData: LiveData<List<OrderData>> = _ordersData
    fun getALlCategories() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedCategories = categoryRespository.getAllCategories()
            _categoriesData.postValue(fetchedCategories)
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

    fun getALlUser() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedData = userRespository.getAllUser()
            _usersData.postValue(fetchedData)
        }
        registerJobFinish()
    }

    fun getALlOrder() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedData = orderRespository.getAllUserOrder()
            _ordersData.postValue(fetchedData)
        }
        registerJobFinish()
    }

    fun updateOrder(orderRequest: OrderRequest, id: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = orderRespository.updateOrder(orderRequest, id)
        }
        registerJobFinish()
    }

    fun createCategory(createCategoryRequest: CreateCategoryRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = categoryRespository.createCategory(createCategoryRequest)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditCategorytFragment_to_adminCategorytFragment)
            }
        }
        registerJobFinish()
    }

    fun updateCategory(id : String,createCategoryRequest: CreateCategoryRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = categoryRespository.updateCategory(id, createCategoryRequest)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditCategorytFragment_to_adminCategorytFragment)
            }
        }
        registerJobFinish()
    }

    fun deleteCategory(id : String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = categoryRespository.deleteCategory(id)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditCategorytFragment_to_adminCategorytFragment)
            }
        }
        registerJobFinish()
    }

    fun createRoom(createRoomRequest: CreateRoomRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = roomRespository.createRoom(createRoomRequest)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditRoomFragment_to_adminRoomFragment)
            }
        }
        registerJobFinish()
    }

    fun updateRoom(id : String,createRoomRequest: CreateRoomRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = roomRespository.updateRoom(id, createRoomRequest)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditRoomFragment_to_adminRoomFragment)
            }
        }
        registerJobFinish()
    }

    fun deleteRoom(id : String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = roomRespository.deleteRoom(id)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminEditRoomFragment_to_adminRoomFragment)
            }
        }
        registerJobFinish()
    }
}

