package com.app.mobile_ecommerece.data.api

object ConstantsURL {
    // Endpoints
//    const val BASE_URL = "http://10.0.2.2:3000/api/" // For local api
    const val BASE_URL = "https://api-nhaxinh.onrender.com/api/"
    const val LOGIN_URL = "user/loginUser"
    const val REGISTER_URL = "user/register"
    const val PROFILE_URL = "user/info-user"
    const val PROFILE_UPDATE_URL = "user/update-user"
    const val CATEGORY_URL = "category/all"
    const val PRODUCT_URL = "product/getAllProduct"
    const val ROOM_URL = "room/all"
    const val CART_URL = "cart/getCart"
    const val CART_ADD_URL = "cart/addtoCart"
    const val CART_UPDATE_URL = "cart/updateCart"
    const val CART_EMPTY_URL = "cart/emptyCart"
    const val ADDRESS_URL = "address"
    const val ADDRESS_EDIT_URL = "address/{id}"
    const val PROVINCE_URL = "https://provinces.open-api.vn/api/p/"
    const val DISCTRICT_URL = "https://provinces.open-api.vn/api/p/{id}/?depth=2"
    const val TOWN_URL = "https://provinces.open-api.vn/api/d/{id}/?depth=2"
}