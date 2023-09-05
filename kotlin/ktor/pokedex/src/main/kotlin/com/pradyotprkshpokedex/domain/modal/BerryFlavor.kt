package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerryFlavor (

    @SerialName("berries"      ) var berries     : List<BerriesPotency> = emptyList(),
    @SerialName("contest_type" ) var contestType : ContestType?       = null,
    @SerialName("id"           ) var id          : Int?               = null,
    @SerialName("name"         ) var name        : String?            = null,
    @SerialName("names"        ) var names       : List<Names>   = emptyList()

)

