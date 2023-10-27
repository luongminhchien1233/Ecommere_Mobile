package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecModel(
    val k: String,

    var v: String,

    val _id: String,
) : Parcelable {

}
