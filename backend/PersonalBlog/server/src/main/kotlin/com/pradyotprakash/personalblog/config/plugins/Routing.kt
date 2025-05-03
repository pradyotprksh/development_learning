package com.pradyotprakash.personalblog.config.plugins

import com.pradyotprakash.personalblog.config.ModulesConfig
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.blog
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting() {
    val blogDataSource by ModulesConfig.di.instance<BlogDataSource>()

    routing {
        blog()
    }
}
