package com.pradyotprakash.unitconverter.features.converter.resource

import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.CONVERT
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.LENGTH
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.WEIGHT
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.TEMPERATURE
import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import io.ktor.resources.Resource

@Resource(CONVERT)
class ConverterResource {
    @Resource(LENGTH)
    data class LengthResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Double,
        val from: LengthTypes,
        val to: LengthTypes,
    )

    @Resource(WEIGHT)
    data class WeightResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Double,
        val from: WeightTypes,
        val to: WeightTypes,
    )

    @Resource(TEMPERATURE)
    data class TemperatureResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Double,
        val from: TemperatureTypes,
        val to: TemperatureTypes,
    )
}