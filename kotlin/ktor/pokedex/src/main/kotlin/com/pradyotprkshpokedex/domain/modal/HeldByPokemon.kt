package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldByPokemon(

    @SerialName("pokemon") val pokemon: NameUrl? = NameUrl(),
    @SerialName("version_details") val versionDetails: List<VersionDetails> = emptyList()

)