package com.app.mobile_ecommerece.model.ThirdParties

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProvinceModel(
    val name: String,
    val code: Int,
    val division_type: String,
    val codename: String,
    val phone_code: Int,
    val districts: List<DisctrictModel>
) : Parcelable {

}