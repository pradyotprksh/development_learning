package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Super(

    @SerialName("use_before") val useBefore: String? = null,
    @SerialName("use_after") val useAfter: String? = null

)