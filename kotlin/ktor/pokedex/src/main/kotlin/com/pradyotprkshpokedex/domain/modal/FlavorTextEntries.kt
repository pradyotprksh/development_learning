package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntries(

    @SerialName("flavor_text") val flavorText: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl(),
    @SerialName("text") val text: String? = null,
    @SerialName("version_group") val versionGroup: NameUrl? = NameUrl(),

    )