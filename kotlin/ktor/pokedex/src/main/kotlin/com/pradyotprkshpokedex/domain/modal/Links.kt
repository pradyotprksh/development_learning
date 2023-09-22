package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links (

    @SerialName("self" ) var self : String? = null,
    @SerialName("git"  ) var git  : String? = null,
    @SerialName("html" ) var html : String? = null

)