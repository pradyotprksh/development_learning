package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Names(

    @SerialName("language") val language: NameUrl? = NameUrl(),
    @SerialName("name") val name: String? = null

)