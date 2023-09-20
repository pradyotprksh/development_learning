package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Version(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("version_group") val versionGroup: NameUrl? = NameUrl()

)

