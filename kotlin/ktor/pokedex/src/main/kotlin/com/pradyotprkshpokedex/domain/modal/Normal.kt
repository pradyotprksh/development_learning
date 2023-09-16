package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Normal (

    @SerialName("use_after"  ) var useAfter  : String?              = null,
    @SerialName("use_before" ) var useBefore : List<NameUrl> = emptyList()

)