package com.pradyotprakash.unitconverter.service.converter

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.UnitConverterClientRequestDetails
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.core.models.response.UnitConversionResponse
import com.pradyotprakash.unitconverter.core.models.response.UnitConverterResponse
import com.pradyotprakash.unitconverter.core.network.NetworkClient
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.CONVERT
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.LENGTH
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.TEMPERATURE
import com.pradyotprakash.unitconverter.utils.Constants.Paths.Converter.WEIGHT

class UnitConverterServiceImplementation(
    private val networkClient: NetworkClient,
) : UnitConverterService {
    override suspend fun unitConverterLength(
        value: Double,
        from: LengthTypes,
        to: LengthTypes,
    ) = networkClient.get<UnitConverterResponse<UnitConversionResponse>>(
        details = UnitConverterClientRequestDetails(
            endpoint = "$CONVERT$LENGTH",
            queries = mapOf(
                "value" to value,
                "from" to from,
                "to" to to,
            )
        )
    ).getOrThrow()

    override suspend fun unitConverterWeight(
        value: Double,
        from: WeightTypes,
        to: WeightTypes,
    ) = networkClient.get<UnitConverterResponse<UnitConversionResponse>>(
        details = UnitConverterClientRequestDetails(
            endpoint = "$CONVERT$WEIGHT",
            queries = mapOf(
                "value" to value,
                "from" to from,
                "to" to to,
            )
        )
    ).getOrThrow()

    override suspend fun unitConverterTemperature(
        value: Double,
        from: TemperatureTypes,
        to: TemperatureTypes,
    ) = networkClient.get<UnitConverterResponse<UnitConversionResponse>>(
        details = UnitConverterClientRequestDetails(
            endpoint = "$CONVERT$TEMPERATURE",
            queries = mapOf(
                "value" to value,
                "from" to from,
                "to" to to,
            )
        )
    ).getOrThrow()
}