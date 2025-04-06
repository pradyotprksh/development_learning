package com.pradyotprakash.unitconverter.features.converter.utils

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes

object LengthConverter {
    private val conversionToMeters = mapOf(
        LengthTypes.MILIMETER to 0.001,
        LengthTypes.CENTIMETER to 0.01,
        LengthTypes.METER to 1.0,
        LengthTypes.KILOMETER to 1_000.0,
        LengthTypes.INCH to 0.0254,
        LengthTypes.FOOT to 0.3048,
        LengthTypes.YARD to 0.9144,
        LengthTypes.MILE to 1_609.344
    )

    fun convert(value: Double, from: LengthTypes, to: LengthTypes): Double {
        val valueInMeters = value * (conversionToMeters[from] ?: error("Unknown unit"))
        return valueInMeters / (conversionToMeters[to] ?: error("Unknown unit"))
    }
}