package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticModel(
    val productCount: Int,

    var order: StatisticOrderModel,

    val userCount: Int,
) : Parcelable {

}