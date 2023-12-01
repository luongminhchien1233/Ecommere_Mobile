package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String
): Parcelable