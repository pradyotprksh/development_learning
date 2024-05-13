package com.pradyotprakash.xfullstack.features.authentication

import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

fun Routing.authentication() {
    post<AuthenticationResource.Login> { }
}