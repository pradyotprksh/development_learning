package com.pradyotprakash.findingfalcone.di

import com.pradyotprakash.findingfalcone.data.services.FindService
import com.pradyotprakash.findingfalcone.data.services.PlanetsService
import com.pradyotprakash.findingfalcone.data.services.TokenService
import com.pradyotprakash.findingfalcone.data.services.VehiclesService
import com.pradyotprakash.findingfalcone.domain.repositories.FindRepositories
import com.pradyotprakash.findingfalcone.domain.repositories.PlanetsRepositories
import com.pradyotprakash.findingfalcone.domain.repositories.TokenRepositories
import com.pradyotprakash.findingfalcone.domain.repositories.VehiclesRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Inject all the repositories required.
 */
@Module
@InstallIn(SingletonComponent::class)
object Repositories {
    @Singleton
    @Provides
    fun providesPlanetsRepository(
        planetsService: PlanetsService,
    ) = PlanetsRepositories(planetsService = planetsService)

    @Singleton
    @Provides
    fun providesVehiclesRepository(
        vehiclesService: VehiclesService,
    ) = VehiclesRepositories(vehiclesService = vehiclesService)

    @Singleton
    @Provides
    fun providesTokenRepository(
        tokenService: TokenService,
    ) = TokenRepositories(tokenService = tokenService)

    @Singleton
    @Provides
    fun providesFineRepository(
        findService: FindService,
    ) = FindRepositories(findService = findService)
}