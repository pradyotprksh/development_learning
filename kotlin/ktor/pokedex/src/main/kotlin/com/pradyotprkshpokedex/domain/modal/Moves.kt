package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Moves(

    @SerialName("move") val move: NameUrl? = NameUrl(),
    @SerialName("version_group_details") val versionGroupDetails: List<VersionGroupDetails> = emptyList()

)