package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAdminDataJson(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: String,
    val wishlist: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val refreshToken: String
) : Parcelable {

}