package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

object ModulesConfig {
    private val berriesModule = Kodein.Module("BERRIES") {
        bind() from singleton { BerriesController() }
    }

    internal val kodein = Kodein {
        import(berriesModule)
    }
}