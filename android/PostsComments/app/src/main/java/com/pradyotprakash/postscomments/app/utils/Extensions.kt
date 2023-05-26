package com.pradyotprakash.postscomments.app.utils

fun String.isValidEmailAddress(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return this.matches(emailRegex.toRegex())
}