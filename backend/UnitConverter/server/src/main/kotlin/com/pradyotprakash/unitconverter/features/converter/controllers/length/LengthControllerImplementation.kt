package com.pradyotprakash.unitconverter.features.converter.controllers.length

import com.pradyotprakash.unitconverter.core.models.response.ConversionResponse
import com.pradyotprakash.unitconverter.core.models.response.ConverterResponse
import com.pradyotprakash.unitconverter.features.converter.resource.ConverterResource
import com.pradyotprakash.unitconverter.features.converter.utils.LengthConverter
import com.pradyotprakash.unitconverter.utils.ConverterResponseStatus
import com.pradyotprakash.unitconverter.utils.Localization
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class LengthControllerImplementation : LengthController {
    override suspend fun convertLength(
        call: ApplicationCall,
        resource: ConverterResource.LengthResource,
    ) {
        val value = resource.value
        val from = resource.from
        val to = resource.to

        try {
            val result = LengthConverter.convert(value, from, to)

            call.respond(
                status = HttpStatusCode.OK,
                message = ConverterResponse(
                    status = ConverterResponseStatus.Success,
                    code = null,
                    message = Localization.CONVERTED_SUCCESSFULLY,
                    data = ConversionResponse(
                        value = result,
                        humanReadable = "$result ${to.abbreviation}"
                    )
                ),
            )
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = ConverterResponse(
                    status = ConverterResponseStatus.Error,
                    code = null,
                    message = e.localizedMessage ?: Localization.DEFAULT_ERROR_MESSAGE,
                    data = null
                ),
            )
        }
    }
}