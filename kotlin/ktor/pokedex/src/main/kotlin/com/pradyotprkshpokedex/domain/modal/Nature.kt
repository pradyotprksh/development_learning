package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nature(

    @SerialName("decreased_stat") val decreasedStat: String? = null,
    @SerialName("hates_flavor") val hatesFlavor: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("increased_stat") val increasedStat: String? = null,
    @SerialName("likes_flavor") val likesFlavor: String? = null,
    @SerialName("move_battle_style_preferences") val moveBattleStylePreferences: List<MoveBattleStylePreferences> = emptyList(),
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokeathlon_stat_changes") val pokeathlonStatChanges: List<PokeathlonStatChanges> = emptyList()

)

