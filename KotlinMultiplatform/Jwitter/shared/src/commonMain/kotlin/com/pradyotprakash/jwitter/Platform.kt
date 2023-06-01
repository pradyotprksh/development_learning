package com.pradyotprakash.jwitter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform