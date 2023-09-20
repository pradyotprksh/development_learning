package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Machine(

    @SerialName("id") val id: Int? = null,
    @SerialName("item") val item: NameUrl? = NameUrl(),
    @SerialName("move") val move: NameUrl? = NameUrl(),
    @SerialName("version_group") val versionGroup: NameUrl? = NameUrl()

)