package com.pradyotprkshpokedex.features.contests

import com.pradyotprkshpokedex.features.contests.controllers.ContestsController
import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.contests(contestsController: ContestsController) {
    get<ContestResource.Type> { contestsController.getAll(this.context, it) }
    get<ContestResource.Type.Id> { contestsController.getDetails(this.context, it) }
    get<ContestResource.Type.Pagination> { contestsController.getByPagination(this.context, it) }

    get<ContestResource.Effect> { contestsController.getAll(this.context, it) }
    get<ContestResource.Effect.Id> { contestsController.getDetails(this.context, it) }
    get<ContestResource.Effect.Pagination> { contestsController.getByPagination(this.context, it) }

    get<ContestResource.SupperContestEffect> { contestsController.getAll(this.context, it) }
    get<ContestResource.SupperContestEffect.Id> { contestsController.getDetails(this.context, it) }
    get<ContestResource.SupperContestEffect.Pagination> { contestsController.getByPagination(this.context, it) }
}