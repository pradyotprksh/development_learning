package com.pradyotprakash.postscomments.core.utils

object PostCommentFormArguments {
    const val formType = "formType"
    const val commentForm = "commentForm"
    const val postForm = "postForm"
    const val formAction = "formAction"
    const val postId = "postId"
    const val commentId = "commentId"
    const val create = "create"
    const val edit = "edit"
    const val na = "na"
}

object PostArguments {
    const val postId = "postId"
}

object FirestoreKeys {
    object Collection {
        const val user = "user"
        const val posts = "posts"
        const val comments = "comments"
    }

    object Keys {
        object Post {
            const val createdOn = "createdOn"
            const val createdBy = "createdBy"
            const val title = "title"
            const val text = "text"
        }

        object Comment {
            const val createdOn = "createdOn"
            const val createdBy = "createdBy"
            const val title = "title"
            const val text = "text"
        }
    }
}