package com.app.mobile_ecommerece.model.Request


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    val username: String,
    val password: String
): Parcelable
