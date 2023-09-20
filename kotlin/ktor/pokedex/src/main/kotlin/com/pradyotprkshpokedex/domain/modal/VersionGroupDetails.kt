package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetails(

    @SerialName("level_learned_at") val levelLearnedAt: Int? = null,
    @SerialName("version_group") val versionGroup: NameUrl? = NameUrl(),
    @SerialName("move_learn_method") val moveLearnMethod: NameUrl? = NameUrl()

)