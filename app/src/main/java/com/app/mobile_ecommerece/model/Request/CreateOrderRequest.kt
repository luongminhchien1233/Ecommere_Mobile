package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import com.app.mobile_ecommerece.model.OrderAddressModel
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateOrderRequest(
    val PaymentMethod: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val addressShipping: OrderAddressModel
): Parcelable