package com.pradyotprakash.splitwise

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform