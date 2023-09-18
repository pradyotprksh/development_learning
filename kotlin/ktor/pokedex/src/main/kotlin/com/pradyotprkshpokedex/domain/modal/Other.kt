package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Other(

    @SerialName("dream_world") var dreamWorld: NameUrl? = NameUrl(),
    @SerialName("home") var home: Home? = Home(),
    @SerialName("official-artwork") var officialArtwork: OfficialArtwork? = OfficialArtwork()

)