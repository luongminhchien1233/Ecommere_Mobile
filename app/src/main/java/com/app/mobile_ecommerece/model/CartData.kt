package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartData(
    val _id: String,
    val products: List<CartModel>,
    val cartTotal: Int,
) : Parcelable {

}