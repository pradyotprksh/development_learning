package com.pradyotprakash.unitconverter.features.converter.controllers.weight

import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.application.ApplicationCall

interface WeightController {
    suspend fun convertWeight(
        call: ApplicationCall,
        resource: ConverterResource.WeightResource,
    )
}