package com.pradyotprakash.unitconverter.features.converter.controllers.temperature

import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.application.ApplicationCall

class TemperatureControllerImplementation: TemperatureController {
    override suspend fun convertTemperature(
        call: ApplicationCall,
        resource: ConverterResource.TemperatureResource,
    ) {
        TODO("Not yet implemented")
    }
}