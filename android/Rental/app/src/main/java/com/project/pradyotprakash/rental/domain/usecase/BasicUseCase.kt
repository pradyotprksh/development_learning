package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
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
    suspend fun getDetails() = flow {
        emit(RenterResponse.Loading)
        delay(2000)
        emit(basicRepository.getDetails())
        emit(RenterResponse.Idle)
    }


    /**
     * Get the terms and condition details based on the user type
     *
     * @param userType Type of the user
     */
    suspend fun getTermsAndCondition(userType: String) = flow {
        emit(RenterResponse.Loading)
        emit(
            basicRepository.getTermsAndCondition(
                userType = userType,
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun isEmailAddressValid(emailAddress: String) = flow {
        emit(RenterResponse.Loading)
        emit(
            basicRepository.isEmailAddressValid(
                emailAddress = emailAddress,
            )
        )
        emit(RenterResponse.Idle)
    }
}