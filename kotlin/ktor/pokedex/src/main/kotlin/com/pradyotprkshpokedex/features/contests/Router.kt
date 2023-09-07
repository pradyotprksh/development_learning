package com.pradyotprkshpokedex.features.contests

import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.contests() {
    get<ContestResource.Type> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.Type.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.Type.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ContestResource.Effect> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.Effect.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.Effect.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ContestResource.SupperContestEffect> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.SupperContestEffect.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ContestResource.SupperContestEffect.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}