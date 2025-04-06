package com.pradyotprakash.unitconverter.features.converter.controllers.weight

import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.application.ApplicationCall

class WeightControllerImplementation: WeightController {
    override suspend fun convertWeight(
        call: ApplicationCall,
        resource: ConverterResource.WeightResource,
    ) {
        TODO("Not yet implemented")
    }
}