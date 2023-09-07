package com.pradyotprkshpokedex.features.moves

import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.moves() {
    get<MovesResource> { }
    get<MovesResource.Id> { }
    get<MovesResource.Pagination> { }

    get<MovesResource.Ailment> { }
    get<MovesResource.Ailment.Id> { }
    get<MovesResource.Ailment.Pagination> { }

    get<MovesResource.BattleStyle> { }
    get<MovesResource.BattleStyle.Id> { }
    get<MovesResource.BattleStyle.Pagination> { }

    get<MovesResource.Category> { }
    get<MovesResource.Category.Id> { }
    get<MovesResource.Category.Pagination> { }

    get<MovesResource.DamageClass> { }
    get<MovesResource.DamageClass.Id> { }
    get<MovesResource.DamageClass.Pagination> { }

    get<MovesResource.LearnMethod> { }
    get<MovesResource.LearnMethod.Id> { }
    get<MovesResource.LearnMethod.Pagination> { }

    get<MovesResource.Target> { }
    get<MovesResource.Target.Id> { }
    get<MovesResource.Target.Pagination> { }
}