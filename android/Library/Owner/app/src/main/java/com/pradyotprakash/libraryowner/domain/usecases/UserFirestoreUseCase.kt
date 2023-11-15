package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.CustomerDetails
import com.pradyotprakash.libraryowner.core.dateTime.TimeUtils
import com.pradyotprakash.libraryowner.core.models.User
import com.pradyotprakash.libraryowner.domain.repositories.UserFirestoreRepository
import javax.inject.Inject

class UserFirestoreUseCase @Inject constructor(
    private val userFirestoreRepository: UserFirestoreRepository,
) {
    suspend fun isUserDetailsAvailable(userId: String) = if (userId.isNotBlank()) {
        userFirestoreRepository.getUserDetails(userId)?.isAllDetailsAdded ?: false
    } else {
        false
    }

    suspend fun setUserDetails(
        userId: String,
        customerDetails: CustomerDetails,
        createdOn: Long?,
    ) {
        val user = User(
            userId = userId,
            name = customerDetails.name,
            emailId = customerDetails.emailId,
            phoneNumber = customerDetails.phoneNumber,
            profileImage = customerDetails.profileImage,
            isAllDetailsAdded = true,
            userCreatedOn = createdOn ?: TimeUtils.getCurrentTimeStamp(),
            detailsUpdatedOn = TimeUtils.getCurrentTimeStamp(),
            isUserALibrarian = true,
            isUserACustomer = false,
        )

        userFirestoreRepository.setUserDetails(user = user)
    }
}