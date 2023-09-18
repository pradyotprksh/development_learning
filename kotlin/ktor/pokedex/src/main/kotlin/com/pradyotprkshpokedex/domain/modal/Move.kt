package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Move(

    @SerialName("accuracy") var accuracy: Int? = null,
    @SerialName("contest_combos") var contestCombos: ContestCombos? = ContestCombos(),
    @SerialName("contest_effect") var contestEffect: ContestEffect? = ContestEffect(),
    @SerialName("contest_type") var contestType: ContestType? = ContestType(),
    @SerialName("damage_class") var damageClass: DamageClass? = DamageClass(),
    @SerialName("effect_chance") var effectChance: String? = null,
    @SerialName("effect_changes") var effectChanges: List<String> = emptyList(),
    @SerialName("effect_entries") var effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("generation") var generation: Generation? = Generation(),
    @SerialName("id") var id: Int? = null,
    @SerialName("learned_by_pokemon") var learnedByPokemon: List<NameUrl> = emptyList(),
    @SerialName("machines") var machines: List<String> = emptyList(),
    @SerialName("meta") var meta: Meta? = Meta(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("past_values") var pastValues: List<String> = emptyList(),
    @SerialName("power") var power: Int? = null,
    @SerialName("pp") var pp: Int? = null,
    @SerialName("priority") var priority: Int? = null,
    @SerialName("stat_changes") var statChanges: List<String> = emptyList(),
    @SerialName("super_contest_effect") var superContestEffect: NameUrl? = NameUrl(),
    @SerialName("target") var target: NameUrl? = NameUrl(),
    @SerialName("type") var type: NameUrl? = NameUrl()

)

