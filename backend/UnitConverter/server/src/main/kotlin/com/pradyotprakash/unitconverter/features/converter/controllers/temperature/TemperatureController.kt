package com.pradyotprakash.unitconverter.features.converter.controllers.temperature

import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.application.ApplicationCall

interface TemperatureController {
    suspend fun convertTemperature(
        call: ApplicationCall,
        resource: ConverterResource.TemperatureResource,
    )
}