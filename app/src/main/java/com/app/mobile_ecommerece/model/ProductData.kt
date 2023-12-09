package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(
    val total: Int,
    val product: List<ProductModel>,
) : Parcelable {

}