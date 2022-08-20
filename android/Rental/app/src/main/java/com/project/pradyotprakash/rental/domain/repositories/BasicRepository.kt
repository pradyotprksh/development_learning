package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.domain.services.BasicService

/**
 * A basic repository class which will initiate the calls
 */
class BasicRepository(
    private val basicService: BasicService,
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
     * Get if the email address is valid or not
     *
     * @param emailAddress Email to be verified
     */
    suspend fun isEmailAddressValid(emailAddress: String) =
        basicService.verifyEmailAddress(emailAddress = emailAddress).parseResponse()
}