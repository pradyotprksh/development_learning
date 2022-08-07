package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.domain.usecase.BasicUseCase

/**
 * A basic repository class which will initiate the calls
 */
class BasicRepository(
    private val basicUseCase: BasicUseCase,
) {
    /**
     * Get the details
     */
    suspend fun getDetails() = basicUseCase.getDetails()

    /**
     * Get the terms and condition details based on the user type
     *
     * @param userType Type of the user
     */
    suspend fun getTermsAndCondition(userType: UserType) =
        basicUseCase.getTermsAndCondition(userType = userType.name.lowercase())

    /**
     * Get the information related to the project from the
     * remote.
     */
    suspend fun getInformation() = basicUseCase.getInformation()
}