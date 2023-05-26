package com.pradyotprakash.postscomments.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.logger.Logger
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.CommentService
import com.pradyotprakash.postscomments.core.utils.FirestoreKeys
import com.pradyotprakash.postscomments.domain.models.CommentDetails
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
                    FirestoreKeys.Keys.Comment.text to text,
                )
            )
            PostsCommentsResponse.Success(true)
        } catch (e: Exception) {
            Logger.e(e.toString())
            PostsCommentsResponse.Error(
                PostsCommentsException(message = e.localizedMessage ?: TR.noDataFoundError)
            )
        }
}