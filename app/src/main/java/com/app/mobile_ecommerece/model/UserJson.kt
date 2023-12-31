package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserJson(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phoneNumber: String
) : Parcelable {

}