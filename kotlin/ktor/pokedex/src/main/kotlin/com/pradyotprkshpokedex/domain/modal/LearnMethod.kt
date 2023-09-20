package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LearnMethod(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("version_groups") val versionGroups: List<NameUrl> = emptyList()

)