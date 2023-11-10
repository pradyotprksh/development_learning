package com.pradyotprakash.libraryowner.data.client

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.pradyotprakash.libraryowner.core.utils.Constants.IP_GEOLOCATION_BASE_URL
import com.pradyotprakash.libraryowner.data.interceptors.IpGeolocationAuthenticationInterceptor
import com.pradyotprakash.libraryowner.di.utils.IpGeolocationClient
import com.pradyotprakash.libraryowner.di.utils.IpGeolocationRetrofit
import com.pradyotprakash.libraryowner.di.utils.UnsplashClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IpGeolocation {
    @Singleton
    @Provides
    @IpGeolocationClient
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        ipGeolocationAuthenticationInterceptor: IpGeolocationAuthenticationInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(ipGeolocationAuthenticationInterceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Singleton
    @Provides
    @IpGeolocationRetrofit
    fun provideRetrofit(@IpGeolocationClient okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(IP_GEOLOCATION_BASE_URL)
        .client(okHttpClient)
        .build()
}