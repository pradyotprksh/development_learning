package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chain(

    @SerialName("evolution_details") var evolutionDetails: List<EvolutionDetails> = emptyList(),
    @SerialName("evolves_to") var evolvesTo: List<EvolvesTo> = emptyList(),
    @SerialName("is_baby") var isBaby: Boolean? = null,
    @SerialName("species") var species: NameUrl? = NameUrl()

)