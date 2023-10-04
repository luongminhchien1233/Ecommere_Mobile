package com.app.mobile_ecommerece.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class OrderStatus(val value: String) : Parcelable {
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED")
}