package com.app.mobile_ecommerece.model.ThirdParties

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TownModel(
    val name: String,
    val code: Int,
    val division_type: String,
    val codename: String,
    val district_code: Int,
) : Parcelable {

}