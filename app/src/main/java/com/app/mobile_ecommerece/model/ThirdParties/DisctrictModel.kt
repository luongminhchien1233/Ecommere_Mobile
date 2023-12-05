package com.app.mobile_ecommerece.model.ThirdParties

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisctrictModel(
    val name: String,
    val code: Int,
    val division_type: String,
    val codename: String,
    val province_code: Int,
    val wards: List<TownModel>
) : Parcelable {

}