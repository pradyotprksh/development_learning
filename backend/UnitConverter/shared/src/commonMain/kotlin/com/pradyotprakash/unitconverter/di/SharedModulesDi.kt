package com.pradyotprakash.unitconverter.di

import com.pradyotprakash.unitconverter.core.network.NetworkClient
import com.pradyotprakash.unitconverter.core.network.UnitConverterHttpClient
import com.pradyotprakash.unitconverter.domain.repositories.converter.UnitConverterRepository
import com.pradyotprakash.unitconverter.domain.repositories.converter.UnitConverterRepositoryImplementation
import com.pradyotprakash.unitconverter.service.converter.UnitConverterService
import com.pradyotprakash.unitconverter.service.converter.UnitConverterServiceImplementation
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

object SharedModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { UnitConverterHttpClient.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }

    private val servicesRemoteModule = DI.Module("SERVICES_REMOTE") {
        bindProvider<UnitConverterService> { UnitConverterServiceImplementation(instance()) }
    }

    private val repositoriesModule = DI.Module("REPOSITORIES") {
        bind<UnitConverterRepository>() with singleton {
            UnitConverterRepositoryImplementation(
                instance(),
            )
        }
    }

    val di = DI {
        importAll(
            networkModule,
            servicesRemoteModule,
            repositoriesModule,
        )
    }

    object Instance {
        val unitConverterRepository: UnitConverterRepository by di.instance()
    }
}