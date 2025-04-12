package com.pradyotprakash.unitconverter.utils

object Localization {
    fun format(key: String, vararg arguments: Any): String {
        var result = key
        for (a in arguments) {
            if (result.contains("%s")) {
                result = result.replaceFirst("%s", a.toString())
            } else {
                break
            }
        }
        return result
    }

    const val APP_NAME = "Converter"
    const val CONVERTED_SUCCESSFULLY = "Successful conversion"
    const val MILIMETER = "mm"
    const val CENTIMETER = "cm"
    const val METER = "m"
    const val KILOMETER = "km"
    const val INCH = "in"
    const val FOOT = "ft"
    const val YARD = "yd"
    const val MILE = "mi"
    const val CELSIUS = "°C"
    const val KELVIN = "K"
    const val FAHRENHEIT = "°F"
    const val MILIGRAM = "mg"
    const val GRAM = "g"
    const val KILOGRAM = "kg"
    const val OUNCE = "oz"
    const val POUND = "pound"
    const val DEFAULT_ERROR_MESSAGE = "Something went wrong. Please try again later"
    const val LENGTH = "Length"
    const val WEIGHT = "Weight"
    const val TEMPERATURE = "Temperature"
    const val ENTER_THE_VALUE = "Enter the %s to convert"
    const val UNIT_TO_CONVERT_FROM = "Unit to convert from"
    const val UNIT_TO_CONVERT_TO = "Unit to convert to"
    const val CONVERT = "Convert"
    const val RESULT = "Result of your conversion"
    const val RESET = "Reset"
}