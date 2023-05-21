package com.pradyotprakash.findingfalcone.di

import com.pradyotprakash.findingfalcone.data.services.FindService
import com.pradyotprakash.findingfalcone.data.services.PlanetsService
import com.pradyotprakash.findingfalcone.data.services.TokenService
import com.pradyotprakash.findingfalcone.data.services.VehiclesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Inject all the services required.
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitServices {
    @Singleton
    @Provides
    fun providePlanetsService(retrofit: Retrofit): PlanetsService =
        retrofit.create(PlanetsService::class.java)

    @Singleton
    @Provides
    fun provideVehiclesService(retrofit: Retrofit): VehiclesService =
        retrofit.create(VehiclesService::class.java)

    @Singleton
    @Provides
    fun provideTokenService(retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)

    @Singleton
    @Provides
    fun provideFindService(retrofit: Retrofit): FindService =
        retrofit.create(FindService::class.java)
}