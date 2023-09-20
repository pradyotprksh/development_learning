package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilingEffect(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("effect_entries") val effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("items") val items: List<NameUrl> = emptyList()

)