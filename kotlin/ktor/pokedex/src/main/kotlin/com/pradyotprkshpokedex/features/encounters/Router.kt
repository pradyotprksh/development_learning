package com.pradyotprkshpokedex.features.encounters

import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.encounters() {
    get<EncountersResource.Methods> { }
    get<EncountersResource.Methods.Id> { }
    get<EncountersResource.Methods.Pagination> { }

    get<EncountersResource.Condition> { }
    get<EncountersResource.Condition.Id> { }
    get<EncountersResource.Condition.Pagination> { }

    get<EncountersResource.ConditionValue> { }
    get<EncountersResource.ConditionValue.Id> { }
    get<EncountersResource.ConditionValue.Pagination> { }
}