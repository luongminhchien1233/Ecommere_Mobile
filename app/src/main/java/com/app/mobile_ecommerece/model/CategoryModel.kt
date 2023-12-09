package com.app.mobile_ecommerece.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val _id: String,

    var nameCate: String,

) : Parcelable {

}