package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genera(

    @SerialName("genus") val genus: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl()

)