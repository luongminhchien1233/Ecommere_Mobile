package com.app.mobile_ecommerece.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.data.repository.CategoryRepository
import com.app.mobile_ecommerece.data.repository.RoomRespository
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.RoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val categoryRespository: CategoryRepository,
    private val roomRespository: RoomRespository,
) : BaseViewModel() {

    private var _categoriesData = MutableLiveData<List<CategoryModel>>()
    val categoriesData: LiveData<List<CategoryModel>> = _categoriesData

    private var _roomsData = MutableLiveData<List<RoomModel>>()
    val roomsData: LiveData<List<RoomModel>> = _roomsData
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
}

