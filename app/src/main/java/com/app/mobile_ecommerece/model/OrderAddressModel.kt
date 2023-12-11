package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderAddressModel(
    val province: String,

    val district: String,

    val ward: String,

    val note: String
    ) : Parcelable {
}