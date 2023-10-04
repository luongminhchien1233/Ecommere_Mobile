package com.app.mobile_ecommerece.data.api

sealed class NetWorkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetWorkResult<T>()
    data class Error(val exception: Exception) : NetWorkResult<Nothing>()
}
