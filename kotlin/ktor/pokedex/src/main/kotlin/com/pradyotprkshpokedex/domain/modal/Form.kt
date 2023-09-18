package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Form(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("form_order") var formOrder: Int? = null,
    @SerialName("is_default") var isDefault: Boolean? = null,
    @SerialName("is_battle_only") var isBattleOnly: Boolean? = null,
    @SerialName("is_mega") var isMega: Boolean? = null,
    @SerialName("form_name") var formName: String? = null,
    @SerialName("pokemon") var pokemon: NameUrl? = NameUrl(),
    @SerialName("sprites") var sprites: Sprites? = Sprites(),
    @SerialName("types") var types: List<Types> = emptyList(),
    @SerialName("version_group") var versionGroup: VersionGroup? = VersionGroup()

)

