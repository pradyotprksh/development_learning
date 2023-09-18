package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Moves(

    @SerialName("move") var move: Move? = Move(),
    @SerialName("version_group_details") var versionGroupDetails: List<VersionGroupDetails> = emptyList()

)