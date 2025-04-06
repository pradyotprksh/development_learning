package com.pradyotprakash.unitconverter.features.converter

import com.pradyotprakash.unitconverter.features.converter.controllers.ConverterController
import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.converter(converterController: ConverterController) {
    get<ConverterResource.LengthResource> {
        converterController.convertLength(
            this.call,
            it,
        )
    }
    get<ConverterResource.WeightResource> {
        converterController.convertWeight(
            this.call,
            it,
        )
    }
    get<ConverterResource.TemperatureResource> {
        converterController.convertTemperature(
            this.call,
            it,
        )
    }
}