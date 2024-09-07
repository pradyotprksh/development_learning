package com.pradyotprakash.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform