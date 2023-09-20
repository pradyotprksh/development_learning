package com.pradyotprkshpokedex.features.moves

import com.pradyotprkshpokedex.features.moves.controllers.MovesController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.moves(movesController: MovesController) {
    get<MovesResource> { movesController.getAll(this.context, it) }
    get<MovesResource.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.Ailment> { movesController.getAll(this.context, it) }
    get<MovesResource.Ailment.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.Ailment.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.BattleStyle> { movesController.getAll(this.context, it) }
    get<MovesResource.BattleStyle.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.BattleStyle.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.Category> { movesController.getAll(this.context, it) }
    get<MovesResource.Category.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.Category.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.DamageClass> { movesController.getAll(this.context, it) }
    get<MovesResource.DamageClass.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.DamageClass.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.LearnMethod> { movesController.getAll(this.context, it) }
    get<MovesResource.LearnMethod.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.LearnMethod.Pagination> { movesController.getByPagination(this.context, it) }

    get<MovesResource.Target> { movesController.getAll(this.context, it) }
    get<MovesResource.Target.Id> { movesController.getDetails(this.context, it) }
    get<MovesResource.Target.Pagination> { movesController.getByPagination(this.context, it) }
}