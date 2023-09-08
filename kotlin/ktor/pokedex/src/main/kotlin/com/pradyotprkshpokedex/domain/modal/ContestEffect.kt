package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestEffect(

    @SerialName("appeal") var appeal: Int? = null,
    @SerialName("effect_entries") var effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("jam") var jam: Int? = null

)

