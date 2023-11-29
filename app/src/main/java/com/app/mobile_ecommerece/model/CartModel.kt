package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartModel(
    val product: ProductCartModel,
    val quantity: Int,
    val totalPriceItem: Int,
    val _id: String
) : Parcelable {

}