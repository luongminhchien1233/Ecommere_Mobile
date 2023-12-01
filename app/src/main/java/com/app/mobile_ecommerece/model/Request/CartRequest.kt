package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartRequest(
    val productId: String,
    val quantity: Int
): Parcelable