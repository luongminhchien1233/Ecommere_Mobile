package com.app.mobile_ecommerece.data.api

object ConstantsURL {
    // Endpoints
//    const val BASE_URL = "http://10.0.2.2:3000/api/" // For local api
    const val BASE_URL = "https://api-nhaxinh.onrender.com/api/"
    const val LOGIN_URL = "user/loginUser"
    const val REGISTER_URL = "user/register"
    const val PROFILE_URL = "user/info-user"
    const val PROFILE_UPDATE_URL = "user/update-user"
    const val GET_ALL_USER = "user/all-users"
    const val UPDATE_ROLE_URL = "user/admin/update/{id}"
    const val CATEGORY_URL = "category/all"
    const val CATEGORY_ADD_URL = "category/create-cate"
    const val CATEGORY_EDIT_URL = "category/{id}"
    const val PRODUCT_URL = "product/getAllProduct"
    const val PRODUCT_ADMIN_URL = "product/admin/getAll"
    const val PRODUCT_CREATE_URL = "product/create-product"
    const val PRODUCT_BYCATE_URL = "product/category/{id}"
    const val PRODUCT_BYROOM_URL = "product/room/{id}"
    const val ROOM_URL = "room/all"
    const val ROOM_ADD_URL = "room/create-room"
    const val ROOM_EDIT_URL = "room/{id}"
    const val ROOM_CATEGORY_URL = "room/getCateByRoom/{id}"
    const val CART_URL = "cart/getCart"
    const val CART_ADD_URL = "cart/addtoCart"
    const val CART_UPDATE_URL = "cart/updateCart"
    const val CART_EMPTY_URL = "cart/emptyCart"
    const val ADDRESS_URL = "address"
    const val ADDRESS_EDIT_URL = "address/{id}"
    const val PROVINCE_URL = "https://provinces.open-api.vn/api/p/"
    const val DISCTRICT_URL = "https://provinces.open-api.vn/api/p/{id}/?depth=2"
    const val TOWN_URL = "https://provinces.open-api.vn/api/d/{id}/?depth=2"
    const val ORDER_URL = "order"
    const val ORDER_GETALL_URL = "order/getAll"
    const val ORDER_GET_URL = "order/myOrder"
    const val ORDER_DETAILS_URL = "order/detail/{id}"
    const val ORDER_UPDATE_ADMIN_URL = "order/admin/{id}"
    const val ORDER_UPDATE_USER_URL = "order/user/{id}"
}