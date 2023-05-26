package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import kotlinx.coroutines.tasks.await

class AuthenticationDataRepository(
    private val auth: FirebaseAuth,
) : AuthenticationService {
    override fun currentUser(): FirebaseUser? = auth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null

    override suspend fun createUserUsingEmailPassword(
        email: String,
        password: String
    ): PostsCommentsResponse<AuthResult> = try {
        val createUserResult = auth.createUserWithEmailAndPassword(email, password).await()
        PostsCommentsResponse.Success(createUserResult)
    } catch (e: Exception) {
        Logger.e(e.toString())
        PostsCommentsResponse.Error(PostsCommentsException(message = e.localizedMessage ?: ""))
    }

    override suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String
    ) = try {
        val createUserResult = auth.signInWithEmailAndPassword(email, password).await()
        PostsCommentsResponse.Success(createUserResult)
    } catch (e: Exception) {
        Logger.e(e.toString())
        PostsCommentsResponse.Error(PostsCommentsException(message = e.localizedMessage ?: ""))
    }

    override fun logoutUser() {
        auth.signOut()
    }
}