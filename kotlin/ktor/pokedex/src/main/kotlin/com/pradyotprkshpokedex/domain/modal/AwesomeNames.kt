package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwesomeNames(

    @SerialName("awesome_name") val awesomeName: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl()

)