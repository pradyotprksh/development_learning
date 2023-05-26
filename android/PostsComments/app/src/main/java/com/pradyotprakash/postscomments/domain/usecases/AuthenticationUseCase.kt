package com.pradyotprakash.postscomments.domain.usecases

import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.core.services.UserService
import com.pradyotprakash.postscomments.device.DeviceUtils
import com.pradyotprakash.postscomments.domain.models.UserDetails
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService,
) {
    fun isUserLoggedIn() = authenticationService.isUserLoggedIn()

    fun getCurrentUserId() = authenticationService.currentUser()?.uid

    fun logoutUser() = authenticationService.logoutUser()

    suspend fun signInUserWithEmailPassword(
        email: String,
        password: String,
    ) = flow {
        emit(PostsCommentsResponse.Loading)
        emit(
            authenticationService.signInUserUsingEmailPassword(
                email = email, password = password
            )
        )
        emit(PostsCommentsResponse.Idle)
    }

    suspend fun createUserWithEmailPassword(
        email: String,
        password: String,
    ) = flow {
        emit(PostsCommentsResponse.Loading)
        emit(
            authenticationService.createUserUsingEmailPassword(
                email = email, password = password
            )
        )
        emit(PostsCommentsResponse.Idle)
    }

    suspend fun createUserDocument(
        email: String,
        name: String
    ) = flow {
        emit(PostsCommentsResponse.Loading)

        val userId = getCurrentUserId()

        if (userId != null) {
            val user = UserDetails(
                emailAddress = email,
                name = name,
                createdOn = DeviceUtils.getCurrentTimestamp(),
                userId = userId,
            )
            emit(
                userService.createUser(user)
            )
        } else {
            emit(
                PostsCommentsResponse.Error(
                    PostsCommentsException(message = TR.noDataFoundError)
                )
            )
        }
        emit(PostsCommentsResponse.Idle)
    }
}