package com.pradyotprkshpokedex.features.encounters

import com.pradyotprkshpokedex.features.encounters.controllers.EncountersController
import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.encounters(encountersController: EncountersController) {
    get<EncountersResource.Methods> { encountersController.getAll(this.context, it) }
    get<EncountersResource.Methods.Id> { encountersController.getDetails(this.context, it) }
    get<EncountersResource.Methods.Pagination> { encountersController.getByPagination(this.context, it) }

    get<EncountersResource.Condition> { encountersController.getAll(this.context, it) }
    get<EncountersResource.Condition.Id> { encountersController.getDetails(this.context, it) }
    get<EncountersResource.Condition.Pagination> { encountersController.getByPagination(this.context, it) }

    get<EncountersResource.ConditionValue> { encountersController.getAll(this.context, it) }
    get<EncountersResource.ConditionValue.Id> { encountersController.getDetails(this.context, it) }
    get<EncountersResource.ConditionValue.Pagination> { encountersController.getByPagination(this.context, it) }
}