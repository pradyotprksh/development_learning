package com.pradyotprakash.pokedexkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform