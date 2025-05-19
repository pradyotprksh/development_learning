package com.pradyotprakash.personalblog.utils

object Localization {
    fun format(key: String, vararg arguments: Any): String {
        var result = key
        for (a in arguments) {
            if (result.contains("%s")) {
                result = result.replaceFirst("%s", a.toString())
            } else {
                break
            }
        }
        return result
    }

    const val APP_NAME = "Personal Blog"
    const val DETAILS_FOUND = "Details found"
    const val DEFAULT_ERROR_MESSAGE = "Something went wrong. Please try again later"
    const val BLOG_CREATED_SUCCESSFULLY = "Blog created successfully"
    const val BLOG_DELETED_SUCCESSFULLY = "Blog deleted successfully"
    const val BLOG_UPDATED_SUCCESSFULLY = "Blog updated successfully"
}