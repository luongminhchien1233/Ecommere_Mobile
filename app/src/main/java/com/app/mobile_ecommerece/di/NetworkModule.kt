package com.app.mobile_ecommerece.di
import com.app.mobile_ecommerece.common.AuthInterceptor
import com.app.mobile_ecommerece.data.api.*
import com.app.mobile_ecommerece.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenRepository: TokenRepository
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(httpLoggingInterceptor)
        builder.interceptors().add(AuthInterceptor(tokenRepository))
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantsURL.BASE_URL)
            .build()
    }

    @Provides
    fun provideCategoryAPI(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    fun provideProductAPI(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun provideRoomAPI(retrofit: Retrofit): RoomApi {
        return retrofit.create(RoomApi::class.java)
    }

    @Provides
    fun provideCartAPI(retrofit: Retrofit): CartApi{
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    fun provideAddressAPI(retrofit: Retrofit): AddressApi{
        return retrofit.create(AddressApi::class.java)
    }

    @Provides
    fun provideOrderAPI(retrofit: Retrofit): OrderApi{
        return retrofit.create(OrderApi::class.java)
    }


}