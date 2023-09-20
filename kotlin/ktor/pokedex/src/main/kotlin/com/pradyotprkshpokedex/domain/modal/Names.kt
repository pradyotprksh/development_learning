package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Names(

    @SerialName("name") val name: String? = null,
    @SerialName("color") val color: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl()

)