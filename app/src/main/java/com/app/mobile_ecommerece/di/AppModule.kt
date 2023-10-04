package com.app.mobile_ecommerece.di

import android.content.Context
import com.app.mobile_ecommerece.common.AppSharePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppSharePreference(@ApplicationContext context: Context): AppSharePreference {
        return AppSharePreference(context)
    }

}