package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveBattleStylePreferences(

    @SerialName("high_hp_preference") val highHpPreference: Int? = null,
    @SerialName("low_hp_preference") val lowHpPreference: Int? = null,
    @SerialName("move_battle_style") val moveBattleStyle: NameUrl? = NameUrl()

)