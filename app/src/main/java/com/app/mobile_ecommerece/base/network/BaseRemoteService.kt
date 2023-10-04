package com.app.mobile_ecommerece.base.network

import com.app.mobile_ecommerece.data.api.NetWorkResult
import com.app.mobile_ecommerece.model.CustomResponse
import com.google.gson.Gson
import retrofit2.Response

open class BaseRemoteService : BaseService() {

    suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): NetWorkResult<T> {
        val response: Response<T>
        try {
            response = execute.invoke()
        } catch (t: Throwable) {
            return NetWorkResult.Error(parseNetworkErrorException(t))
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                val errBody: CustomResponse<Nothing> = Gson().fromJson<CustomResponse<Nothing>>(
                    response.errorBody()?.string(),
                    CustomResponse::class.java
                )
                NetWorkResult.Error(
                    BaseNetworkException(
                        responseMessage = "Response without body, Message ${errBody.message}",
                        responseCode = 200
                    )
                )
            } else {
                NetWorkResult.Success(response.body()!!)
            }
        } else {
            val errBody: CustomResponse<Nothing> = Gson().fromJson<CustomResponse<Nothing>>(
                response.errorBody()?.string(),
                CustomResponse::class.java
            )
            NetWorkResult.Error(
                parseError(
                    response.message(),
                    response.code(),
                    errBody.message
                )
            )
        }
    }
}