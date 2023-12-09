package com.app.mobile_ecommerece.model

import com.google.gson.annotations.SerializedName

data class CustomResponse<T>(
    var status: String,

    val data: T? = null,

    var message: String,

)

