package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilingEffect(

    @SerialName("effect_entries") var effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("items") var items: List<NameUrl> = emptyList(),
    @SerialName("name") var name: String? = null

)
