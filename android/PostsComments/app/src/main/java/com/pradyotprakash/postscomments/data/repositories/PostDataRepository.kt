package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.PostService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.PostDetails
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class PostDataRepository(
    private val firestore: FirebaseFirestore,
) : PostService {
    override suspend fun createPost(postDetails: PostDetails): PostsCommentsResponse<Boolean> =
        try {
            firestore.collection(FirestoreKeys.Collection.posts).add(postDetails).await()
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(
                PostsCommentsException(message = e.localizedMessage ?: TR.noDataFoundError)
            )
        }

    override suspend fun getPosts(): Flow<PostsCommentsResponse<List<PostDetails>>> {
        return callbackFlow {
            val subscription = firestore.collection(FirestoreKeys.Collection.posts)
                .addSnapshotListener { data, error ->
                    if (error != null) {
                        trySend(
                            PostsCommentsResponse.Error(
                                PostsCommentsException(
                                    message = error.localizedMessage ?: TR.noDataFoundError
                                )
                            )
                        )
                    } else {
                        if (data != null) {
                            Logger.e(data.documents.toString())
                            val post = data.toObjects(PostDetails::class.java)
                            trySend(PostsCommentsResponse.Success(post))
                        }
                    }
                }
            awaitClose { subscription.remove() }
        }
    }
}