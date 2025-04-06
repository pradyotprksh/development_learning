package com.pradyotprakash.unitconverter.features.converter.utils

import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes

object TemperatureConverter {
    fun convert(value: Double, from: TemperatureTypes, to: TemperatureTypes): Double {
        return when (from) {
            TemperatureTypes.CELSIUS -> when (to) {
                TemperatureTypes.CELSIUS -> value
                TemperatureTypes.FAHRENHEIT -> (value * 9 / 5) + 32
                TemperatureTypes.KELVIN -> value + 273.15
            }

            TemperatureTypes.FAHRENHEIT -> when (to) {
                TemperatureTypes.CELSIUS -> (value - 32) * 5 / 9
                TemperatureTypes.FAHRENHEIT -> value
                TemperatureTypes.KELVIN -> (value - 32) * 5 / 9 + 273.15
            }

            TemperatureTypes.KELVIN -> when (to) {
                TemperatureTypes.CELSIUS -> value - 273.15
                TemperatureTypes.FAHRENHEIT -> (value - 273.15) * 9 / 5 + 32
                TemperatureTypes.KELVIN -> value
            }
        }
    }
}