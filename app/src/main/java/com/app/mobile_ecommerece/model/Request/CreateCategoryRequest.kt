package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import com.app.mobile_ecommerece.model.OrderAddressModel
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateCategoryRequest(
    val nameCate: String,
    val roomId: String,
): Parcelable