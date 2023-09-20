package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperContestEffect(

    @SerialName("id") val id: Int? = null,
    @SerialName("appeal") val appeal: Int? = null,
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("moves") val moves: List<NameUrl> = emptyList(),
    @SerialName("url") val url: String? = null

)
