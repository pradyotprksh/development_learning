package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nature(

    @SerialName("decreased_stat") var decreasedStat: String? = null,
    @SerialName("hates_flavor") var hatesFlavor: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("increased_stat") var increasedStat: String? = null,
    @SerialName("likes_flavor") var likesFlavor: String? = null,
    @SerialName("move_battle_style_preferences") var moveBattleStylePreferences: List<MoveBattleStylePreferences> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokeathlon_stat_changes") var pokeathlonStatChanges: List<PokeathlonStatChanges> = emptyList()

)

