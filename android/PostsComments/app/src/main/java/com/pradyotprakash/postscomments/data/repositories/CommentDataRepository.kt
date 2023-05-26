package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.CommentService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.CommentCompleteDetails
import com.pradyotprakash.postscomments.domain.models.CommentDetails
import com.pradyotprakash.postscomments.domain.models.PostCompleteDetails
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CommentDataRepository(
    private val firestore: FirebaseFirestore,
): CommentService {
    override suspend fun createComment(commentDetails: CommentDetails): PostsCommentsResponse<Boolean>  =
        try {
            firestore.collection(FirestoreKeys.Collection.comments).add(commentDetails).await()
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(
                PostsCommentsException(message = e.localizedMessage ?: TR.noDataFoundError)
            )
        }

    override suspend fun updateComment(
        text: String,
        commentId: String
    ): PostsCommentsResponse<Boolean> =
        try {
            firestore.collection(FirestoreKeys.Collection.comments).document(commentId).update(
                mapOf(
                    FirestoreKeys.Keys.Comment.comment to text,
                )
            )
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(
                PostsCommentsException(message = e.localizedMessage ?: TR.noDataFoundError)
            )
        }

    override suspend fun getComments(postId: String): Flow<PostsCommentsResponse<List<CommentCompleteDetails>>> {
        return callbackFlow {
            val subscription = firestore.collection(FirestoreKeys.Collection.comments)
                .whereEqualTo(FirestoreKeys.Keys.Comment.postId, postId)
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
                            val posts = ArrayList<CommentCompleteDetails>()
                            data.documents.forEach { document ->
                                val comment = document.getString(FirestoreKeys.Keys.Comment.comment)
                                val createdBy =
                                    document.getString(FirestoreKeys.Keys.Comment.createdBy)
                                val createdOn =
                                    document.getLong(FirestoreKeys.Keys.Comment.createdOn) ?: 0L

                                if (comment != null && createdBy != null) {
                                    posts.add(
                                        CommentCompleteDetails(
                                            comment = comment,
                                            postId = postId,
                                            createdBy = createdBy,
                                            createdOn = createdOn,
                                            commentId = document.id,
                                            userDetails = null
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
}