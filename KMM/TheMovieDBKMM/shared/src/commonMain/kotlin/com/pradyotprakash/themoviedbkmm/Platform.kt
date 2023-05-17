package com.pradyotprakash.themoviedbkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform