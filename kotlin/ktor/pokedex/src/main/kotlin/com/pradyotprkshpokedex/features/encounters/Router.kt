package com.pradyotprkshpokedex.features.encounters

import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.encounters() {
    get<EncountersResource.Methods> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.Methods.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.Methods.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<EncountersResource.Condition> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.Condition.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.Condition.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<EncountersResource.ConditionValue> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.ConditionValue.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<EncountersResource.ConditionValue.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}