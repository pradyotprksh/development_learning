package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Super(

    @SerialName("use_after") var useAfter: String? = null,
    @SerialName("use_before") var useBefore: String? = null

)