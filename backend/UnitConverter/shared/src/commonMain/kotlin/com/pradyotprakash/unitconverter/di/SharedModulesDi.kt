package com.pradyotprakash.unitconverter.di

import com.pradyotprakash.unitconverter.core.network.UnitConverterHttpClient
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object SharedModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { UnitConverterHttpClient.createHttpClient() }
    }

    val di = DI {
        importAll(
            networkModule,
        )
    }
}