package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genera(

    @SerialName("genus") var genus: String? = null,
    @SerialName("language") var language: NameUrl? = NameUrl()

)