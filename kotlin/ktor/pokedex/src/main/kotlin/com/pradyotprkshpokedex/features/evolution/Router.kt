package com.pradyotprkshpokedex.features.evolution

import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionsController
import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.evolution(evolutionsController: EvolutionsController) {
    get<EvolutionResource.Chains> { evolutionsController.getAll(this.context, it) }
    get<EvolutionResource.Chains.Id> { evolutionsController.getDetails(this.context, it) }
    get<EvolutionResource.Chains.Pagination> { evolutionsController.getByPagination(this.context, it) }

    get<EvolutionResource.Triggers> { evolutionsController.getAllEvolutionTrigger(this.context, it) }
    get<EvolutionResource.Triggers.Id> { evolutionsController.getEvolutionTriggerDetails(this.context, it) }
    get<EvolutionResource.Triggers.Pagination> {
        evolutionsController.getEvolutionTriggerByPagination(
            this.context,
            it
        )
    }
}