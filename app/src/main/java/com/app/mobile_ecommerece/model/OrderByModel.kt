package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderByModel(
    val _id: String,
    val firstname: String,
    val lastname: String,
    ): Parcelable