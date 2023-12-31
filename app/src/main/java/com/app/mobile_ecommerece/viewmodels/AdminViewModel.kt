package com.app.mobile_ecommerece.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.*
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.model.Request.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val categoryRespository: CategoryRepository,
    private val roomRespository: RoomRespository,
    private val userRespository: UserRepository,
    private val orderRespository: OrderRespository,
    private val tokenRespository: TokenRepository,
    private val productRespository: ProductRespository
) : BaseViewModel() {

    private var _categoriesData = MutableLiveData<List<CategoryModel>>()
    val categoriesData: LiveData<List<CategoryModel>> = _categoriesData

    private var _roomsData = MutableLiveData<List<RoomModel>>()
    val roomsData: LiveData<List<RoomModel>> = _roomsData

    private var _usersData = MutableLiveData<List<UserAdminDataJson>>()
    val usersData: LiveData<List<UserAdminDataJson>> = _usersData

    private var _ordersData = MutableLiveData<List<OrderData>>()
    val ordersData: LiveData<List<OrderData>> = _ordersData

    private var _productsData = MutableLiveData<List<ProductAdminModel>>()
    val productsData: LiveData<List<ProductAdminModel>> = _productsData

    private var _statisticData = MutableLiveData<StatisticModel>()
    val statisticData: LiveData<StatisticModel> = _statisticData

    private var _isEnableSuccess = MutableLiveData<Boolean>()
    val isEnableSuccess: LiveData<Boolean> = _isEnableSuccess

    private var _isUpdateSuccess = MutableLiveData<Boolean>()
    val isUpdateSuccess: LiveData<Boolean> = _isUpdateSuccess

    private var _isUpdateFail = MutableLiveData<Boolean>()
    val isUpdateFail: LiveData<Boolean> = _isUpdateFail
    fun isAdmin(): Boolean? {
        if(tokenRespository.getRole() == "admin"){
            return true
        }
        else{
            return false
        }
    }

    fun getRole(): String {
        return tokenRespository.getRole().toString()
    }

    fun updateRole(role: UpdateRoleRequest, id: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = userRespository.updateRole(role, id)
            if(data.status == "success"){
                navigateToPage(R.id.action_adminUserInfoFragment_to_adminUserFragment)
            }
        }
        registerJobFinish()
    }

    fun getAllProducts() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedData = productRespository.getAllByAdmin()
            _productsData.postValue(fetchedData)
        }
        registerJobFinish()
    }

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

    fun adminGetStatistic() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = userRespository.adminGetStatistic()
            _statisticData.postValue(data)
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

    fun getCategoryByRoom(id: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val fetchedCategories = roomRespository.getCategoryByRoom(id)
            _categoriesData.postValue(fetchedCategories)
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

    fun createProduct(
        images: List<MultipartBody.Part>,
        code: RequestBody,
        name: RequestBody,
        description: RequestBody,
        shortDescription: RequestBody,
        category: RequestBody,
        room: RequestBody,
        specs: RequestBody,
        price: Int,
        quantity: Int
    ) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val data = productRespository.createProduct(images, code, name, description, shortDescription, category, room, specs, price, quantity)
        }
        registerJobFinish()
    }

    fun enableProduct(enableRequest: ProductEnableRequest, id: String) {
        _isEnableSuccess.postValue(false)
        parentJob = viewModelScope.launch(handler) {
            val data = productRespository.enableProduct(enableRequest, id)
            if(data.status == "success"){
                getAllProducts()
                _isEnableSuccess.postValue(true)
            }
        }
        registerJobFinish()
    }

    fun updateProduct(updateRequest: ProductUpdateRequest, id: String) {
        _isUpdateSuccess.postValue(false)
        _isUpdateFail.postValue(false)
        parentJob = viewModelScope.launch(handler) {
            val data = productRespository.updateProduct(updateRequest, id)
            if(data.status == "success"){
                _isUpdateSuccess.postValue(true)
            }
            else{
                _isUpdateFail.postValue(true)
            }
        }
        registerJobFinish()
    }

    fun getInfoProduct(roomId: String){
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val rooms = roomRespository.getAllRooms()
            _roomsData.postValue(rooms)
            for(item in rooms){
                if(item._id == roomId){
                    val category = roomRespository.getCategoryByRoom(item._id)
                    _categoriesData.postValue(category)
                }
            }
        }
        registerJobFinish()
    }

}

