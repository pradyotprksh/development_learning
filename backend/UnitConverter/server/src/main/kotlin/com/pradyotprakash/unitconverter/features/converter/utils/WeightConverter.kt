package com.pradyotprakash.unitconverter.features.converter.utils

import com.pradyotprakash.unitconverter.core.models.request.WeightTypes

object WeightConverter {
    fun convert(value: Double, from: WeightTypes, to: WeightTypes): Double {
        val inGrams = when (from) {
            WeightTypes.MILIGRAM -> value / 1000
            WeightTypes.GRAM -> value
            WeightTypes.KILOGRAM -> value * 1000
            WeightTypes.OUNCE -> value * 28.3495
            WeightTypes.POUND -> value * 453.592
        }

        return when (to) {
            WeightTypes.MILIGRAM -> inGrams * 1000
            WeightTypes.GRAM -> inGrams
            WeightTypes.KILOGRAM -> inGrams / 1000
            WeightTypes.OUNCE -> inGrams / 28.3495
            WeightTypes.POUND -> inGrams / 453.592
        }
    }
}