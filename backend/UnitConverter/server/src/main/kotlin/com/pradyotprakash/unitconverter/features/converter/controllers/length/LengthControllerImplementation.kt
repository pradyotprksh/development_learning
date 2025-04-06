package com.pradyotprakash.unitconverter.features.converter.controllers.length

import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.application.ApplicationCall

class LengthControllerImplementation: LengthController {
    override suspend fun convertLength(
        call: ApplicationCall,
        resource: ConverterResource.LengthResource,
    ) {
        TODO("Not yet implemented")
    }
}