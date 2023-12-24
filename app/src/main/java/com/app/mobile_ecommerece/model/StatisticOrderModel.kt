package com.app.mobile_ecommerece.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticOrderModel(
    val orderCount: Int,

    var orderPriceSumMonth: Int,

) : Parcelable {

}