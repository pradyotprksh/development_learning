package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chain(

    @SerialName("is_baby") val isBaby: Boolean? = null,
    @SerialName("species") val species: NameUrl? = NameUrl(),
    @SerialName("evolution_details") var evolutionDetails: List<EvolutionDetails> = emptyList(),
    @SerialName("evolves_to") val evolvesTo: List<EvolvesTo> = emptyList()

)