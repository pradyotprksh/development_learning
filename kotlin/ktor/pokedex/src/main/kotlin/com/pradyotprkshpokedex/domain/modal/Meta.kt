package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta (

    @SerialName("ailment"        ) var ailment       : Ailment?  = Ailment(),
    @SerialName("ailment_chance" ) var ailmentChance : Int?      = null,
    @SerialName("category"       ) var category      : Category? = Category(),
    @SerialName("crit_rate"      ) var critRate      : Int?      = null,
    @SerialName("drain"          ) var drain         : Int?      = null,
    @SerialName("flinch_chance"  ) var flinchChance  : Int?      = null,
    @SerialName("healing"        ) var healing       : Int?      = null,
    @SerialName("max_hits"       ) var maxHits       : String?   = null,
    @SerialName("max_turns"      ) var maxTurns      : String?   = null,
    @SerialName("min_hits"       ) var minHits       : String?   = null,
    @SerialName("min_turns"      ) var minTurns      : String?   = null,
    @SerialName("stat_chance"    ) var statChance    : Int?      = null

)