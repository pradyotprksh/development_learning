package com.pradyotprkshpokedex.features.moves

import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing

fun Routing.moves() {
    get<MovesResource> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.Ailment> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Ailment.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Ailment.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.BattleStyle> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.BattleStyle.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.BattleStyle.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.Category> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Category.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Category.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.DamageClass> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.DamageClass.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.DamageClass.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.LearnMethod> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.LearnMethod.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.LearnMethod.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<MovesResource.Target> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Target.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<MovesResource.Target.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}