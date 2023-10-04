package com.app.mobile_ecommerece.utils

import com.google.android.gms.auth.api.identity.BeginSignInResult

sealed class SignInState {
    data class Success(val result: BeginSignInResult) : SignInState()
    data class Failure(val message: String?) : SignInState()
}
