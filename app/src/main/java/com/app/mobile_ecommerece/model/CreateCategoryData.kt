package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateCategoryData(
    val nameCate: String,
    val icUrl: String,
    val _id: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
) : Parcelable {

}