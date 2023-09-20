package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class Area(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("game_index") val gameIndex: Int? = null,
    @SerialName("encounter_method_rates") val encounterMethodRates: List<EncounterMethodRates> = emptyList(),
    @SerialName("location") val location: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokemon_encounters") val pokemonEncounters: List<PokemonEncounters> = emptyList()

)

