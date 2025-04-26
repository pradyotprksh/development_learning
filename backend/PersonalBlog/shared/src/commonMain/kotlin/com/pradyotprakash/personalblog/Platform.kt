package com.pradyotprakash.personalblog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform