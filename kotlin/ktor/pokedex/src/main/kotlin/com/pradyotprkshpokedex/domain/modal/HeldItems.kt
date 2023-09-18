package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldItems(

    @SerialName("item") var item: NameUrl? = NameUrl(),
    @SerialName("version_details") var versionDetails: List<NameUrl> = emptyList()

)