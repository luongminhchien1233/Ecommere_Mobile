package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCartModel(
    val _id: String,
    val name: String,
    val description: String,
    val images: List<ImageModel>,
    val specs: List<SpecModel>,
    val priceSale: Int,
    ) : Parcelable {

}