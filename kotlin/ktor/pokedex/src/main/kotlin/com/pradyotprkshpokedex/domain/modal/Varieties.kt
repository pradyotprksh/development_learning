package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Varieties(

    @SerialName("is_default") var isDefault: Boolean? = null,
    @SerialName("pokemon") var pokemon: NameUrl? = NameUrl()

)