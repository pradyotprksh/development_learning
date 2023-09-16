package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LearnMethod (

    @SerialName("descriptions"   ) var descriptions  : List<Descriptions>  = emptyList(),
    @SerialName("id"             ) var id            : Int?                     = null,
    @SerialName("name"           ) var name          : String?                  = null,
    @SerialName("names"          ) var names         : List<Names>         = emptyList(),
    @SerialName("version_groups" ) var versionGroups : List<NameUrl> = emptyList()

)
