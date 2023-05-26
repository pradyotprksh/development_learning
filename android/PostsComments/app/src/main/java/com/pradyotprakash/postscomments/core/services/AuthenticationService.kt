package com.pradyotprakash.postscomments.core.services

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse

interface AuthenticationService {
    fun currentUser(): FirebaseUser?

    fun isUserLoggedIn(): Boolean

    suspend fun createUserUsingEmailPassword(
        email: String,
        password: String,
    ): PostsCommentsResponse<AuthResult>

    suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String,
    ): PostsCommentsResponse<AuthResult>

    fun logoutUser()
}