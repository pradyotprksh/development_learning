package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Abilities(

    @SerialName("is_hidden") var isHidden: Boolean? = null,
    @SerialName("slot") var slot: Int? = null,
    @SerialName("ability") var ability: Ability? = Ability()

)