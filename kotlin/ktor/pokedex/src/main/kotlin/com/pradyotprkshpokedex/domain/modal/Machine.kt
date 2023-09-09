package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Machine(

    @SerialName("id") var id: Int? = null,
    @SerialName("item") var item: NameUrl? = NameUrl(),
    @SerialName("move") var move: NameUrl? = NameUrl(),
    @SerialName("version_group") var versionGroup: NameUrl? = NameUrl()

)

