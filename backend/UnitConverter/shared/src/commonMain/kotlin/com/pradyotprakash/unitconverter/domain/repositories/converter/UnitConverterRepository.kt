package com.pradyotprakash.unitconverter.domain.repositories.converter

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.core.models.response.ClientResponse
import com.pradyotprakash.unitconverter.core.models.response.UnitConversionResponse
import com.pradyotprakash.unitconverter.core.models.response.UnitConverterResponse
import kotlinx.coroutines.flow.Flow

interface UnitConverterRepository {
    suspend fun unitConverterLength(
        value: String,
        from: LengthTypes,
        to: LengthTypes,
    ): Flow<ClientResponse<out UnitConverterResponse<UnitConversionResponse>>>

    suspend fun unitConverterWeight(
        value: String,
        from: WeightTypes,
        to: WeightTypes,
    ): Flow<ClientResponse<out UnitConverterResponse<UnitConversionResponse>>>

    suspend fun unitConverterTemperature(
        value: String,
        from: TemperatureTypes,
        to: TemperatureTypes,
    ): Flow<ClientResponse<out UnitConverterResponse<UnitConversionResponse>>>
}