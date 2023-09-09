package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Descriptions(

    @SerialName("description") var description: String? = null,
    @SerialName("language") var language: NameUrl? = NameUrl()

)