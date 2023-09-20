package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Form(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("form_order") val formOrder: Int? = null,
    @SerialName("is_default") val isDefault: Boolean? = null,
    @SerialName("is_battle_only") val isBattleOnly: Boolean? = null,
    @SerialName("is_mega") val isMega: Boolean? = null,
    @SerialName("form_name") val formName: String? = null,
    @SerialName("pokemon") val pokemon: NameUrl? = NameUrl(),
    @SerialName("sprites") val sprites: Sprites? = Sprites(),
    @SerialName("types") val types: List<Types> = emptyList(),
    @SerialName("version_group") val versionGroup: VersionGroup? = VersionGroup()

)

