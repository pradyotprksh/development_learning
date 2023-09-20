package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stat(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("game_index") val gameIndex: Int? = null,
    @SerialName("is_battle_only") val isBattleOnly: Boolean? = null,
    @SerialName("affecting_moves") val affectingMoves: AffectingMoves? = AffectingMoves(),
    @SerialName("affecting_natures") val affectingNatures: AffectingNatures? = AffectingNatures(),
    @SerialName("characteristics") val characteristics: List<Characteristics> = emptyList(),
    @SerialName("move_damage_class") val moveDamageClass: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList()

)

