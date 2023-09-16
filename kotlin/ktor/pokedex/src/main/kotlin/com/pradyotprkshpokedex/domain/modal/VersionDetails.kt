package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionDetails (

    @SerialName("rate"    ) var rate    : Int?     = null,
    @SerialName("version" ) var version : Version? = Version()

)