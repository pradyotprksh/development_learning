package com.pradyotprkshpokedex.features.pokemon

import com.pradyotprkshpokedex.features.pokemon.controllers.PokemonsController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.pokemon(pokemonsController: PokemonsController) {
    get<PokemonResource.Ability> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Ability.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Ability.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Characteristics> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Characteristics.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Characteristics.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.EggGroup> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.EggGroup.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.EggGroup.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Gender> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Gender.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Gender.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.GrowthRate> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.GrowthRate.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.GrowthRate.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Nature> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Nature.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Nature.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.PokeathlonStat> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.PokeathlonStat.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.PokeathlonStat.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Encounters.Id> { pokemonsController.getDetails(this.context, it) }

    get<PokemonResource.Color> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Color.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Color.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Form> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Form.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Form.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Habitat> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Habitat.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Habitat.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Shape> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Shape.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Shape.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Species> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Species.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Species.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Stat> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Stat.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Stat.Pagination> { pokemonsController.getByPagination(this.context, it) }

    get<PokemonResource.Type> { pokemonsController.getAll(this.context, it) }
    get<PokemonResource.Type.Id> { pokemonsController.getDetails(this.context, it) }
    get<PokemonResource.Type.Pagination> { pokemonsController.getByPagination(this.context, it) }
}