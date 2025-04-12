package com.pradyotprakash.unitconverter.domain.repositories.converter

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.core.models.response.ClientResponse
import com.pradyotprakash.unitconverter.service.converter.UnitConverterService
import com.pradyotprakash.unitconverter.utils.ConverterResponseStatus
import com.pradyotprakash.unitconverter.utils.Localization.DEFAULT_ERROR_MESSAGE
import kotlinx.coroutines.flow.flow

class UnitConverterRepositoryImplementation(
    private val unitConverterService: UnitConverterService,
) : UnitConverterRepository {
    override suspend fun unitConverterLength(
        value: String,
        from: LengthTypes,
        to: LengthTypes,
    ) = flow {
        emit(ClientResponse.Loading)
        try {
            val response = unitConverterService.unitConverterLength(
                value = value.toDouble(),
                from = from,
                to = to,
            )
            if (response.status == ConverterResponseStatus.Success) {
                emit(ClientResponse.Success(response))
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: DEFAULT_ERROR_MESSAGE,
                    ),
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: DEFAULT_ERROR_MESSAGE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }

    override suspend fun unitConverterWeight(
        value: String,
        from: WeightTypes,
        to: WeightTypes,
    ) = flow {
        emit(ClientResponse.Loading)
        try {
            val response = unitConverterService.unitConverterWeight(
                value = value.toDouble(),
                from = from,
                to = to,
            )
            if (response.status == ConverterResponseStatus.Success) {
                emit(ClientResponse.Success(response))
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: DEFAULT_ERROR_MESSAGE,
                    ),
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: DEFAULT_ERROR_MESSAGE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }

    override suspend fun unitConverterTemperature(
        value: String,
        from: TemperatureTypes,
        to: TemperatureTypes,
    ) = flow {
        emit(ClientResponse.Loading)
        try {
            val response = unitConverterService.unitConverterTemperature(
                value = value.toDouble(),
                from = from,
                to = to,
            )
            if (response.status == ConverterResponseStatus.Success) {
                emit(ClientResponse.Success(response))
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: DEFAULT_ERROR_MESSAGE,
                    ),
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: DEFAULT_ERROR_MESSAGE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }
}