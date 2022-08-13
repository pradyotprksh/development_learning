package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import javax.inject.Inject

/**
 * Use case for the the basic services, will be used to connect to the
 * service classes.
 */
class BasicUseCase @Inject constructor(
    private val basicRepository: BasicRepository
) {
    /**
     * Get the details
     */
    suspend fun getDetails() = basicRepository.getDetails()


    /**
     * Get the terms and condition details based on the user type
     *
     * @param userType Type of the user
     */
    suspend fun getTermsAndCondition(userType: String) =
        basicRepository.getTermsAndCondition(userType = userType)
}