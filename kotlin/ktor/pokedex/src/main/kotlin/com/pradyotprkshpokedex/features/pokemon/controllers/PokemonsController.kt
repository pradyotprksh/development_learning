package com.pradyotprkshpokedex.features.pokemon.controllers

class PokemonsController(
    private val abilitiesController: AbilitiesController,
    private val characteristicsController: CharacteristicsController,
    private val eggGroupsController: EggGroupsController,
    private val gendersController: GendersController,
    private val growthRatesController: GrowthRatesController,
    private val naturesController: NaturesController,
    private val pokeathlonStatController: PokeathlonStatController,
    private val pokemonColorsController: PokemonColorsController,
    private val pokemonFormsController: PokemonFormsController,
    private val pokemonHabitatsController: PokemonHabitatsController,
    private val pokemonLocationAreasController: PokemonLocationAreasController,
    private val pokemonController: PokemonController,
    private val pokemonShapesController: PokemonShapesController,
    private val pokemonSpeciesController: PokemonSpeciesController,
    private val statsController: StatsController,
    private val typesController: TypesController,
) : AbilitiesController by abilitiesController, CharacteristicsController by characteristicsController,
    EggGroupsController by eggGroupsController, GendersController by gendersController,
    GrowthRatesController by growthRatesController, NaturesController by naturesController,
    PokeathlonStatController by pokeathlonStatController, PokemonColorsController by pokemonColorsController,
    PokemonFormsController by pokemonFormsController, PokemonHabitatsController by pokemonHabitatsController,
    PokemonLocationAreasController by pokemonLocationAreasController, PokemonController by pokemonController,
    PokemonShapesController by pokemonShapesController, PokemonSpeciesController by pokemonSpeciesController,
    StatsController by statsController, TypesController by typesController {
}