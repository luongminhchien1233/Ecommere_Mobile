package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomModel(
    val _id: String,

    var nameRoom: String,

    val icUrl: String,
) : Parcelable {

}