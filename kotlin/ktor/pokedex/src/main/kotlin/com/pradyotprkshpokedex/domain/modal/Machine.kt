package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Machine(

    @SerialName("id") var id: Int? = null,
    @SerialName("item") var item: Item? = null,
    @SerialName("move") var move: Move? = null,
    @SerialName("version_group") var versionGroup: VersionGroup? = null

)

