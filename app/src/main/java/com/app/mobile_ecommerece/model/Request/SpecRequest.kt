package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecRequest(
    val k: String,
    val v: String,
): Parcelable