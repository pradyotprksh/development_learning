package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetails(

    @SerialName("level_learned_at") var levelLearnedAt: Int? = null,
    @SerialName("version_group") var versionGroup: NameUrl? = NameUrl(),
    @SerialName("move_learn_method") var moveLearnMethod: NameUrl? = NameUrl()

)