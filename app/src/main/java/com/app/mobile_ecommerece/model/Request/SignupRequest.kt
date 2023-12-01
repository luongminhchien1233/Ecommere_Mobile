package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String
): Parcelable