package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DamageRelations(

    @SerialName("no_damage_to") val noDamageTo: List<NameUrl> = emptyList(),
    @SerialName("half_damage_to") val halfDamageTo: List<NameUrl> = emptyList(),
    @SerialName("double_damage_to") val doubleDamageTo: List<NameUrl> = emptyList(),
    @SerialName("no_damage_from") val noDamageFrom: List<NameUrl> = emptyList(),
    @SerialName("half_damage_from") val halfDamageFrom: List<NameUrl> = emptyList(),
    @SerialName("double_damage_from") val doubleDamageFrom: List<NameUrl> = emptyList()

)