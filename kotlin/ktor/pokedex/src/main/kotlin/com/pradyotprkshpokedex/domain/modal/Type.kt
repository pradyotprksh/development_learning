package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("damage_relations") var damageRelations: DamageRelations? = DamageRelations(),
    @SerialName("past_damage_relations") var pastDamageRelations: List<PastDamageRelations> = emptyList(),
    @SerialName("game_indices") var gameIndices: List<GameIndices> = emptyList(),
    @SerialName("generation") var generation: NameUrl? = NameUrl(),
    @SerialName("move_damage_class") var moveDamageClass: NameUrl? = NameUrl(),
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokemon") var pokemon: List<PokemonDetails> = emptyList(),
    @SerialName("moves") var moves: List<NameUrl> = emptyList()

)

