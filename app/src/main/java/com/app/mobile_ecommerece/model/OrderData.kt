package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderData(
    val addressShipping: OrderAddressModel,
    val _id: String,
    val PaymentMethod: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val products: List<OrderProductModel>,
    val total: Int,
    val status: String,
    val orderby: String,
    val orderTime: String
) : Parcelable {

}