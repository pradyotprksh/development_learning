package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.domain.services.BasicService
import kotlinx.coroutines.flow.flow

/**
 * A basic repository class which will initiate the calls
 */
class BasicRepository(
    private val basicService: BasicService,
) {
    /**
     * Get the details
     */
    suspend fun getDetails(appCheckToken: String) = basicService.details(
        appCheckToken = appCheckToken,
    ).parseResponse()


    /**
     * Get the terms and condition details based on the user type
     *
     * @param userType Type of the user
     */
    suspend fun getTermsAndCondition(userType: String, appCheckToken: String) =
        basicService.termsAndCondition(userType = userType, appCheckToken = appCheckToken)
            .parseResponse()

    /**
     * Get if the email address is valid or not
     *
     * @param emailAddress Email to be verified
     */
    suspend fun isEmailAddressValid(emailAddress: String, appCheckToken: String) = flow {
        emit(RenterResponse.Loading)
        emit(
            basicService.verifyEmailAddress(
                emailAddress = emailAddress,
                appCheckToken = appCheckToken
            ).parseResponse()
        )
        emit(RenterResponse.Idle)
    }
}