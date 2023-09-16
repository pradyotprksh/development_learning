package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Area (

    @SerialName("encounter_method_rates" ) var encounterMethodRates : List<EncounterMethodRates> = emptyList(),
    @SerialName("game_index"             ) var gameIndex            : Int?                            = null,
    @SerialName("id"                     ) var id                   : Int?                            = null,
    @SerialName("location"               ) var location             : NameUrl?                       = NameUrl(),
    @SerialName("name"                   ) var name                 : String?                         = null,
    @SerialName("names"                  ) var names                : List<Names>                = emptyList(),
    @SerialName("pokemon_encounters"     ) var pokemonEncounters    : List<NameUrl>    = emptyList()

)

