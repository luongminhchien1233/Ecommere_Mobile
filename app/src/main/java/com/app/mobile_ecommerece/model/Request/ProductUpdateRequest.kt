package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUpdateRequest(
    val code: String,
    val name: String,
    val description: String,
    val shortDescription: String,
    val category: String,
    val room: String,
    val price: Int,
    val sale: Int,
    val quantity: Int,
    val specs: List<SpecRequest>,
    val enable: Boolean
): Parcelable