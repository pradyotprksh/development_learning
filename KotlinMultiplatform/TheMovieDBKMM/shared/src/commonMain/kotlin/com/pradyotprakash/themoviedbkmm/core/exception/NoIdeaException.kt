package com.pradyotprakash.themoviedbkmm.core.exception

class NoIdeaException: Exception() {
    override val message: String
        get() = "Something went wrong and we have no idea."
}