package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestType (

    @SerialName("name" ) val name : String? = null,
    @SerialName("url"  ) val url  : String? = null

)