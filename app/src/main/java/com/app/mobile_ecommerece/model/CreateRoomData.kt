package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateRoomData(
    val _id: String,
    val nameRoom: String,
    val icUrl: String,
    val categories: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
) : Parcelable {

}