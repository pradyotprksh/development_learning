package com.pradyotprakash.unitconverter.core.models.request

import com.pradyotprakash.unitconverter.utils.Localization

enum class LengthTypes(val abbreviation: String) {
    MILIMETER(Localization.MILIMETER),
    CENTIMETER(Localization.CENTIMETER),
    METER(Localization.METER),
    KILOMETER(Localization.KILOMETER),
    INCH(Localization.INCH),
    FOOT(Localization.FOOT),
    YARD(Localization.YARD),
    MILE(Localization.MILE),
}

enum class WeightTypes(val abbreviation: String) {
    MILIGRAM(Localization.MILIGRAM),
    GRAM(Localization.GRAM),
    KILOGRAM(Localization.KILOGRAM),
    OUNCE(Localization.OUNCE),
    POUND(Localization.POUND),
}

enum class TemperatureTypes(val abbreviation: String) {
    CELSIUS(Localization.CELSIUS),
    FAHRENHEIT(Localization.FAHRENHEIT),
    KELVIN(Localization.KELVIN),
}