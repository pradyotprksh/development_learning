package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.PostService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.PostCompleteDetails
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

    override suspend fun getPosts(): Flow<PostsCommentsResponse<List<PostCompleteDetails>>> {
        return callbackFlow {
            val subscription = firestore.collection(FirestoreKeys.Collection.posts)
                .orderBy(FirestoreKeys.Keys.Post.createdOn)
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
                            val posts = ArrayList<PostCompleteDetails>()
                            data.documents.forEach { document ->
                                val title = document.getString(FirestoreKeys.Keys.Post.title)
                                val text = document.getString(FirestoreKeys.Keys.Post.text)
                                val createdBy = document.getString(FirestoreKeys.Keys.Post.createdBy)
                                val createdOn = document.getLong(FirestoreKeys.Keys.Post.createdOn) ?: 0L

                                if (title != null && text != null && createdBy != null) {
                                    posts.add(
                                        PostCompleteDetails(
                                            title = title,
                                            text = text,
                                            createdBy = createdBy,
                                            createdOn = createdOn,
                                            postId = document.id,
                                        ),
                                    )
                                }
                            }
                            trySend(PostsCommentsResponse.Success(posts.toList()))
                        }
                    }
                }
            awaitClose { subscription.remove() }
        }
    }

    override suspend fun deletePost(postId: String): PostsCommentsResponse<Boolean> =
        try {
            firestore.collection(FirestoreKeys.Collection.posts).document(postId).delete().await()
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(
                PostsCommentsException(message = e.localizedMessage ?: TR.noDataFoundError)
            )
        }
}