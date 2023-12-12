package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressModel(
    val _id: String,
    val nameAddress: String,
    val province: String,
    val district: String,
    val ward: String,
    val note: String,
    var default: Boolean
) : Parcelable {
    fun toAddressOrderModel(): OrderAddressModel {
        return OrderAddressModel(province, district, ward, note)
    }
}