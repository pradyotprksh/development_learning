package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Other(

    @SerialName("dream_world") val dreamWorld: DreamWorld? = DreamWorld(),
    @SerialName("home") val home: Home? = Home(),
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork? = OfficialArtwork()

)