package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressRequest(
    val nameAddress: String,
    val province: String,
    val district: String,
    val ward: String,
    val note: String,
    val default: Boolean
): Parcelable