package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.UserService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.UserDetails
import kotlinx.coroutines.tasks.await

class UserDataRepository(
    private val firestore: FirebaseFirestore,
) : UserService {
    override suspend fun createUser(userDetails: UserDetails): PostsCommentsResponse<Boolean> =
        try {
            firestore.collection(FirestoreKeys.Collection.user)
                .document(userDetails.userId).set(userDetails).await()
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(PostsCommentsException(message = e.localizedMessage ?: ""))
        }
}