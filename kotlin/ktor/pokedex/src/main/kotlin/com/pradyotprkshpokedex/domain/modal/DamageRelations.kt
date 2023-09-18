package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DamageRelations(

    @SerialName("no_damage_to") var noDamageTo: List<NameUrl> = emptyList(),
    @SerialName("half_damage_to") var halfDamageTo: List<NameUrl> = emptyList(),
    @SerialName("double_damage_to") var doubleDamageTo: List<NameUrl> = emptyList(),
    @SerialName("no_damage_from") var noDamageFrom: List<NameUrl> = emptyList(),
    @SerialName("half_damage_from") var halfDamageFrom: List<NameUrl> = emptyList(),
    @SerialName("double_damage_from") var doubleDamageFrom: List<NameUrl> = emptyList()

)