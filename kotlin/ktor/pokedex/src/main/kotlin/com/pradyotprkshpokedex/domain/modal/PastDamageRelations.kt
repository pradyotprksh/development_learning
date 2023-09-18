package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastDamageRelations(

    @SerialName("generation") var generation: Generation? = Generation(),
    @SerialName("damage_relations") var damageRelations: DamageRelations? = DamageRelations()

)