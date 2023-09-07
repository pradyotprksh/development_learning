package com.pradyotprkshpokedex.features.pokemon

import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.pokemon() {
    get<PokemonResource.Ability> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Ability.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Ability.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Characteristics> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Characteristics.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Characteristics.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.EggGroup> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.EggGroup.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.EggGroup.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Gender> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Gender.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Gender.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.GrowthRate> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.GrowthRate.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.GrowthRate.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Nature> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Nature.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Nature.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.PokeathlonStat> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.PokeathlonStat.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.PokeathlonStat.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Encounters> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Encounters.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Encounters.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Color> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Color.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Color.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Form> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Form.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Form.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Habitat> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Habitat.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Habitat.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Shape> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Shape.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Shape.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Species> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Species.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Species.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Stat> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Stat.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Stat.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<PokemonResource.Type> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Type.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<PokemonResource.Type.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}