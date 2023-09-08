package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestTypeDetails(

    @SerialName("appeal") var appeal: Int? = null,
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("moves") var moves: List<Moves> = emptyList()

)
