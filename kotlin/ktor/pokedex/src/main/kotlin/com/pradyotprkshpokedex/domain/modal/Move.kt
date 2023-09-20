package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Move(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("accuracy") val accuracy: Int? = null,
    @SerialName("effect_chance") val effectChance: String? = null,
    @SerialName("pp") val pp: Int? = null,
    @SerialName("priority") val priority: Int? = null,
    @SerialName("power") val power: Int? = null,
    @SerialName("contest_combos") val contestCombos: ContestCombos? = ContestCombos(),
    @SerialName("contest_type") val contestType: NameUrl? = NameUrl(),
    @SerialName("contest_effect") val contestEffect: ContestEffect? = ContestEffect(),
    @SerialName("damage_class") val damageClass: NameUrl? = NameUrl(),
    @SerialName("effect_entries") val effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("effect_changes") val effectChanges: List<String> = emptyList(),
    @SerialName("generation") val generation: NameUrl? = NameUrl(),
    @SerialName("meta") val meta: Meta? = Meta(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("past_values") val pastValues: List<String> = emptyList(),
    @SerialName("stat_changes") val statChanges: List<String> = emptyList(),
    @SerialName("super_contest_effect") val superContestEffect: SuperContestEffect? = SuperContestEffect(),
    @SerialName("target") val target: NameUrl? = NameUrl(),
    @SerialName("type") val type: NameUrl? = NameUrl(),
    @SerialName("learned_by_pokemon") val learnedByPokemon: List<NameUrl> = emptyList(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList()

)

