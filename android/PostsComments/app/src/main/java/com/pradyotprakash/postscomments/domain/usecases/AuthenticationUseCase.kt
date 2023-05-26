package com.pradyotprakash.postscomments.domain.usecases

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    fun getCurrentUser() = authenticationService.currentUser()

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
}