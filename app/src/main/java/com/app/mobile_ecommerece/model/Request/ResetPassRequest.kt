package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResetPassRequest(
    val otpCode: String,
    val email: String,
    val password: String
): Parcelable