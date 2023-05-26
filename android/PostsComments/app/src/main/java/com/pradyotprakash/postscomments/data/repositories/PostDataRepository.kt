package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.PostService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.Post
import kotlinx.coroutines.tasks.await

class PostDataRepository(
    private val firestore: FirebaseFirestore,
) : PostService {
    override suspend fun createPost(post: Post): PostsCommentsResponse<Boolean> = try {
        firestore.collection(FirestoreKeys.Collection.posts).add(post).await()
        PostsCommentsResponse.Success(true)
    } catch (e: Exception) {
        Logger.e(e.toString())
        PostsCommentsResponse.Error(PostsCommentsException(message = e.localizedMessage ?: ""))
    }
}