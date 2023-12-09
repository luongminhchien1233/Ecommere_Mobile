package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomData(
    val nameRoom: String,
    val categories: List<CategoryModel>,
) : Parcelable {

}