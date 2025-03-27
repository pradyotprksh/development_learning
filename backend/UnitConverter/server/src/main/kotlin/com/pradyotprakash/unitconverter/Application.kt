package com.pradyotprakash.unitconverter

import com.pradyotprakash.unitconverter.config.setup
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    setup().start(wait = true)
}
