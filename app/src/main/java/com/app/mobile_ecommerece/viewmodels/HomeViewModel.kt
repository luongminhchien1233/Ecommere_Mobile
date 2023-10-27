package com.app.mobile_ecommerece.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseViewModel
import com.app.mobile_ecommerece.common.Event
import com.app.mobile_ecommerece.data.repository.CategoryRepository
import com.app.mobile_ecommerece.data.repository.ProductRespository
import com.app.mobile_ecommerece.data.repository.UserRepository
import com.app.mobile_ecommerece.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productRespository: ProductRespository
) : BaseViewModel() {

    private var _categoriesData = MutableLiveData<List<CategoryModel>>()
    val categoriesData: LiveData<List<CategoryModel>> = _categoriesData

    private var _productsData = MutableLiveData<List<ProductModel>>()
    val productsData: LiveData<List<ProductModel>> = _productsData

    var isProductsLoading = MutableLiveData<Event<Boolean>>()

    var isFetchDataSuccess = MutableLiveData<Event<Boolean>>()

    fun fetchData() {
        showLoading(true)
        isFetchDataSuccess.postValue(Event(false))
        parentJob = viewModelScope.launch(handler) {
            delay(1000)

            val categoriesDeferred = async { categoryRepository.getAllCategories() }
            val productsDeferred = async { productRespository.getAllProducts() }
//            val userDeferred = async { userRepository.getUserProfile() }
//
//            _userLiveData.postValue(userDeferred.await())

            val fetchedCategories = categoriesDeferred.await()
            val fetchedProducts = productsDeferred.await()
            _categoriesData.postValue(fetchedCategories)

//            _categoryRadioData.postValue(toListCategoryRadioButton(fetchedCategories))
            _productsData.postValue(fetchedProducts)
            isFetchDataSuccess.postValue(Event(true))
        }
        registerJobFinish()
    }

    fun Test(){
        Log.d("alo", categoriesData.value!!.size.toString())
    }
}