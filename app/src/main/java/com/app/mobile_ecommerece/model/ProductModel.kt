package com.app.mobile_ecommerece.model

import android.os.Parcelable
import android.widget.GridLayout.Spec
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductModel(
    val _id: String,
    var code: String,
    val name: String,
    val slug: String,
    val description: String,
    val shortDescription: String,
    val images: List<ImageModel>,
    val category: CategoryModel,
    val room: RoomModel,
    val specs: List<SpecModel>,
    val price: Int,
    val sale: Int,
    val priceSale: Int,
    val quantity: Int,
    val totalrating: String
) : Parcelable {

}

