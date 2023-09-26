package domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonImage(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String? = null,
)