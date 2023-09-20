package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Species(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("gender_rate") val genderRate: Int? = null,
    @SerialName("capture_rate") val captureRate: Int? = null,
    @SerialName("base_happiness") val baseHappiness: Int? = null,
    @SerialName("is_baby") val isBaby: Boolean? = null,
    @SerialName("is_legendary") val isLegendary: Boolean? = null,
    @SerialName("is_mythical") val isMythical: Boolean? = null,
    @SerialName("hatch_counter") val hatchCounter: Int? = null,
    @SerialName("has_gender_differences") val hasGenderDifferences: Boolean? = null,
    @SerialName("forms_switchable") val formsSwitchable: Boolean? = null,
    @SerialName("growth_rate") val growthRate: NameUrl? = NameUrl(),
    @SerialName("pokedex_numbers") val pokedexNumbers: List<PokedexNumbers> = emptyList(),
    @SerialName("egg_groups") val eggGroups: List<NameUrl> = emptyList(),
    @SerialName("color") val color: NameUrl? = NameUrl(),
    @SerialName("shape") val shape: NameUrl? = NameUrl(),
    @SerialName("evolves_from_species") val evolvesFromSpecies: NameUrl? = NameUrl(),
    @SerialName("evolution_chain") val evolutionChain: EvolutionChain? = EvolutionChain(),
    @SerialName("habitat") val habitat: NameUrl? = null,
    @SerialName("generation") val generation: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("form_descriptions") val formDescriptions: List<FormDescriptions> = emptyList(),
    @SerialName("genera") val genera: List<Genera> = emptyList(),
    @SerialName("varieties") val varieties: List<Varieties> = emptyList()

)

