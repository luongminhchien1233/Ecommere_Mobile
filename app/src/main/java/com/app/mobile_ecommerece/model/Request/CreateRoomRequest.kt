package com.app.mobile_ecommerece.model.Request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class CreateRoomRequest(
    val nameRoom: String,
    val icUrl: String,
): Parcelable