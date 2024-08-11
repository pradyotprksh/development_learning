package com.pradyotprakash.ecom

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform