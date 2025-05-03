package com.pradyotprakash.personalblog.config.plugins

import com.pradyotprakash.personalblog.config.ModulesConfig
import com.pradyotprakash.personalblog.features.blog.blog
import com.pradyotprakash.personalblog.features.blog.controllers.BlogController
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting() {
    val blogController by ModulesConfig.di.instance<BlogController>()

    routing {
        blog(
            blogController = blogController,
        )
    }
}
