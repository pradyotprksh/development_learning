package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location (

    @SerialName("areas"        ) var areas       : List<Areas>       = emptyList(),
    @SerialName("game_indices" ) var gameIndices : List<GameIndices> = emptyList(),
    @SerialName("id"           ) var id          : Int?                   = null,
    @SerialName("name"         ) var name        : String?                = null,
    @SerialName("names"        ) var names       : List<Names>       = emptyList(),
    @SerialName("region"       ) var region      : NameUrl?                = NameUrl()

)
