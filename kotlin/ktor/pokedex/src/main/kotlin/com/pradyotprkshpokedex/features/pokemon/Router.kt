package com.pradyotprkshpokedex.features.pokemon

import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.pokemon() {
    get<PokemonResource.Ability> { }
    get<PokemonResource.Ability.Id> { }
    get<PokemonResource.Ability.Pagination> { }

    get<PokemonResource.Characteristics> { }
    get<PokemonResource.Characteristics.Id> { }
    get<PokemonResource.Characteristics.Pagination> { }

    get<PokemonResource.EggGroup> { }
    get<PokemonResource.EggGroup.Id> { }
    get<PokemonResource.EggGroup.Pagination> { }

    get<PokemonResource.Gender> { }
    get<PokemonResource.Gender.Id> { }
    get<PokemonResource.Gender.Pagination> { }

    get<PokemonResource.GrowthRate> { }
    get<PokemonResource.GrowthRate.Id> { }
    get<PokemonResource.GrowthRate.Pagination> { }

    get<PokemonResource.Nature> { }
    get<PokemonResource.Nature.Id> { }
    get<PokemonResource.Nature.Pagination> { }

    get<PokemonResource.PokeathlonStat> { }
    get<PokemonResource.PokeathlonStat.Id> { }
    get<PokemonResource.PokeathlonStat.Pagination> { }

    get<PokemonResource> { }
    get<PokemonResource.Id> { }
    get<PokemonResource.Pagination> { }

    get<PokemonResource.Encounters> { }
    get<PokemonResource.Encounters.Id> { }
    get<PokemonResource.Encounters.Pagination> { }

    get<PokemonResource.Color> { }
    get<PokemonResource.Color.Id> { }
    get<PokemonResource.Color.Pagination> { }

    get<PokemonResource.Form> { }
    get<PokemonResource.Form.Id> { }
    get<PokemonResource.Form.Pagination> { }

    get<PokemonResource.Habitat> { }
    get<PokemonResource.Habitat.Id> { }
    get<PokemonResource.Habitat.Pagination> { }

    get<PokemonResource.Shape> { }
    get<PokemonResource.Shape.Id> { }
    get<PokemonResource.Shape.Pagination> { }

    get<PokemonResource.Species> { }
    get<PokemonResource.Species.Id> { }
    get<PokemonResource.Species.Pagination> { }

    get<PokemonResource.Stat> { }
    get<PokemonResource.Stat.Id> { }
    get<PokemonResource.Stat.Pagination> { }

    get<PokemonResource.Type> { }
    get<PokemonResource.Type.Id> { }
    get<PokemonResource.Type.Pagination> { }
}