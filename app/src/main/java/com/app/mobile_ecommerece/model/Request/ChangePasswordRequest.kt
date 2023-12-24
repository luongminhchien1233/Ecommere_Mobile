package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ChangePasswordRequest(
    val password: String,
): Parcelable