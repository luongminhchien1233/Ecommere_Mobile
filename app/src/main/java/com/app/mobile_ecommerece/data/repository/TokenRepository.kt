package com.app.mobile_ecommerece.data.repository

import com.app.mobile_ecommerece.common.AppSharePreference
import com.app.mobile_ecommerece.model.TokenModel
import javax.inject.Inject

class TokenRepository @Inject constructor(private val appSharePreference: AppSharePreference) {
    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val ROLE = "role"
        private const val REMEMBER = "remember_me_logg"
    }

    fun saveToken(accessToken: String) {
        saveAccessToken(accessToken)
    }

    fun saveToken(tokenModel: TokenModel) {
        saveToken(tokenModel.accessToken)
    }

    private fun saveAccessToken(token: String) {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, token).apply()
    }

    fun getAccessToken(): String? {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun setRemember(remember: Boolean) {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        sharedPreferences.edit().putBoolean(REMEMBER, remember).apply()
    }

    fun getRemember(): Boolean {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        return sharedPreferences.getBoolean(REMEMBER, false)
    }

    fun setRole(role: String) {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        sharedPreferences.edit().putString(ROLE, role).apply()
    }

    fun getRole(): String? {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        return sharedPreferences.getString(ROLE, null)
    }

    fun removeToken() {
        val sharedPreferences = appSharePreference.getSharedPreferences()
        sharedPreferences.edit().remove(ACCESS_TOKEN_KEY).apply()
        sharedPreferences.edit().putBoolean(REMEMBER, false).apply()
        sharedPreferences.edit().remove(ROLE).apply()
    }

    fun checkIsLogin(): Boolean {
        if (getAccessToken().isNullOrEmpty())
            return false
        return true
    }
}