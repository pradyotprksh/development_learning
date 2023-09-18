package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stat(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("game_index") var gameIndex: Int? = null,
    @SerialName("is_battle_only") var isBattleOnly: Boolean? = null,
    @SerialName("affecting_moves") var affectingMoves: AffectingMoves? = AffectingMoves(),
    @SerialName("affecting_natures") var affectingNatures: AffectingNatures? = AffectingNatures(),
    @SerialName("characteristics") var characteristics: List<Characteristics> = emptyList(),
    @SerialName("move_damage_class") var moveDamageClass: NameUrl? = NameUrl(),
    @SerialName("names") var names: List<Names> = emptyList()

)

