package com.pradyotprakash.glassbridgegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform