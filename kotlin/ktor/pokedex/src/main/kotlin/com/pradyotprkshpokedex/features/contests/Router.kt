package com.pradyotprkshpokedex.features.contests

import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.contests() {
    get<ContestResource.Type> { }
    get<ContestResource.Type.Id> { }
    get<ContestResource.Type.Pagination> { }

    get<ContestResource.Effect> { }
    get<ContestResource.Effect.Id> { }
    get<ContestResource.Effect.Pagination> { }

    get<ContestResource.SupperContestEffect> { }
    get<ContestResource.SupperContestEffect.Id> { }
    get<ContestResource.SupperContestEffect.Pagination> { }
}