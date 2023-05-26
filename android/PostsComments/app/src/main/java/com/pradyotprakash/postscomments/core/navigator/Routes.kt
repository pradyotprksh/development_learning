package com.pradyotprakash.postscomments.core.navigator

import com.pradyotprakash.postscomments.core.utils.PostArguments
import com.pradyotprakash.postscomments.core.utils.PostCommentFormArguments

fun Routes.path(): String {
    if (arguments.isEmpty()) return route
    var completePath = route
    for (argument in arguments) {
        completePath += "{${argument}}/"
    }
    return completePath.removeSuffix("/")
}

enum class Routes(
    val route: String,
    val arguments: List<String> = emptyList()
) {
    Splash("splash/"),
    Posts("posts/"),
    SignUp("sign-up/"),
    Login("login/"),
    PostForm(
        "post-form/",
        arguments = listOf(
            PostCommentFormArguments.formType,
            PostCommentFormArguments.formAction,
            PostCommentFormArguments.postId,
            PostCommentFormArguments.commentId,
        )
    ),
    Post(
        "post/",
        arguments = listOf(
            PostArguments.postId
        )
    ),
}