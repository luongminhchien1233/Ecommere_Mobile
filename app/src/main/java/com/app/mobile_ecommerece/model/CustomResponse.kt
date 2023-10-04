package com.app.mobile_ecommerece.model

import com.google.gson.annotations.SerializedName

data class CustomResponse<T>(
    var success: String,

    var message: String,

    val data: T? = null,
)

