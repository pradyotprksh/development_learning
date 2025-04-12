package com.pradyotprakash.unitconverter.service.converter

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.core.models.response.UnitConversionResponse
import com.pradyotprakash.unitconverter.core.models.response.UnitConverterResponse

interface UnitConverterService {
    suspend fun unitConverterLength(
        value: Double,
        from: LengthTypes,
        to: LengthTypes,
    ): UnitConverterResponse<UnitConversionResponse>

    suspend fun unitConverterWeight(
        value: Double,
        from: WeightTypes,
        to: WeightTypes,
    ): UnitConverterResponse<UnitConversionResponse>

    suspend fun unitConverterTemperature(
        value: Double,
        from: TemperatureTypes,
        to: TemperatureTypes,
    ): UnitConverterResponse<UnitConversionResponse>
}