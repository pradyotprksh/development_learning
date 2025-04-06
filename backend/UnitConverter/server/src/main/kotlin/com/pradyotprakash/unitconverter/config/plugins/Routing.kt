package com.pradyotprakash.unitconverter.config.plugins

import com.pradyotprakash.unitconverter.config.ModulesConfig
import com.pradyotprakash.unitconverter.features.converter.controllers.ConverterController
import com.pradyotprakash.unitconverter.features.converter.converter
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting() {
    val converterController by ModulesConfig.di.instance<ConverterController>()

    routing {
        converter(
            converterController = converterController,
        )
    }
}
