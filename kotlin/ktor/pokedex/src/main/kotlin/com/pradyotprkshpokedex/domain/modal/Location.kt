package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("region") val region: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("game_indices") val gameIndices: List<GameIndices> = emptyList(),
    @SerialName("areas") val areas: List<NameUrl> = emptyList()

)