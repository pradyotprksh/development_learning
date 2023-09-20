package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("damage_relations") val damageRelations: DamageRelations? = DamageRelations(),
    @SerialName("past_damage_relations") val pastDamageRelations: List<PastDamageRelations> = emptyList(),
    @SerialName("game_indices") val gameIndices: List<GameIndices> = emptyList(),
    @SerialName("generation") val generation: NameUrl? = NameUrl(),
    @SerialName("move_damage_class") val moveDamageClass: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokemon") val pokemon: List<PokemonDetails> = emptyList(),
    @SerialName("moves") val moves: List<NameUrl> = emptyList()

)

