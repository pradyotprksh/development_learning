package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.domain.services.BasicService
import javax.inject.Inject

class BasicUseCase @Inject constructor(
    private val basicService: BasicService
) {
    /**
     * Get the details
     */
    suspend fun getDetails() = basicService.details().parseResponse()


    /**
     * Get the terms and condition details based on the user type
     *
     * @param userType Type of the user
     */
    suspend fun getTermsAndCondition(userType: String) =
        basicService.termsAndCondition(userType = userType).parseResponse()

    /**
     * Get the information related to the project from the
     * remote.
     */
    suspend fun getInformation() = basicService.information().parseResponse()
}