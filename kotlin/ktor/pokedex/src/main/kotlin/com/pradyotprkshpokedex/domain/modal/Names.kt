package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Names (

    @SerialName("language" ) var language : Language? = null,
    @SerialName("name"     ) var name     : String?   = null

)