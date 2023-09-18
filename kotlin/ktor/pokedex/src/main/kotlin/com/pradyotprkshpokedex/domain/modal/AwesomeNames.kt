package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwesomeNames(

    @SerialName("awesome_name") var awesomeName: String? = null,
    @SerialName("language") var language: NameUrl? = NameUrl()

)