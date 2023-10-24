package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenJson(
    val id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val token: String,
) : Parcelable {
    val name
        get(): String {
            return "$firstname $lastname"
        }

    fun isValidateFirstName(): Boolean {
        return firstname.isNotEmpty()
    }

    fun isValidateLastName(): Boolean {
        return lastname.isNotEmpty()
    }
}