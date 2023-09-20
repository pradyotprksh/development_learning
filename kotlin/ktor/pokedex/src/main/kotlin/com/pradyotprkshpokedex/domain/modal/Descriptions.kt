package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Descriptions(

    @SerialName("description") val description: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl()

)