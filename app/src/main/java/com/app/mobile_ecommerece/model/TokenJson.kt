package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenJson(
    val token: String,
    val role: String
) : Parcelable {
    fun toTokenModel(): TokenModel {
        return TokenModel(token)
    }
}