package com.pradyotprakash.unitconverter.features.converter.resource

import com.pradyotprakash.unitconverter.Constants.Paths.Converter.CONVERT
import com.pradyotprakash.unitconverter.Constants.Paths.Converter.LENGTH
import com.pradyotprakash.unitconverter.Constants.Paths.Converter.WEIGHT
import com.pradyotprakash.unitconverter.Constants.Paths.Converter.TEMPERATURE
import com.pradyotprakash.unitconverter.LengthTypes
import com.pradyotprakash.unitconverter.TemperatureTypes
import com.pradyotprakash.unitconverter.WeightTypes
import io.ktor.resources.Resource

@Resource(CONVERT)
class ConverterResource {
    @Resource(LENGTH)
    data class LengthResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Long,
        val from: LengthTypes,
        val to: LengthTypes,
    )

    @Resource(WEIGHT)
    data class WeightResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Long,
        val from: WeightTypes,
        val to: WeightTypes,
    )

    @Resource(TEMPERATURE)
    data class TemperatureResource(
        private val parent: ConverterResource = ConverterResource(),
        val value: Long,
        val from: TemperatureTypes,
        val to: TemperatureTypes,
    )
}