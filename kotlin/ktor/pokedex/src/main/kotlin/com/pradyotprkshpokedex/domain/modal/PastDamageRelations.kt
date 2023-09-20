package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastDamageRelations(

    @SerialName("generation") val generation: Generation? = Generation(),
    @SerialName("damage_relations") val damageRelations: DamageRelations? = DamageRelations()

)