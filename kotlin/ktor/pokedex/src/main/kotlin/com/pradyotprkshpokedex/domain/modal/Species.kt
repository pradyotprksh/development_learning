package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class Species(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("gender_rate") var genderRate: Int? = null,
    @SerialName("capture_rate") var captureRate: Int? = null,
    @SerialName("base_happiness") var baseHappiness: Int? = null,
    @SerialName("is_baby") var isBaby: Boolean? = null,
    @SerialName("is_legendary") var isLegendary: Boolean? = null,
    @SerialName("is_mythical") var isMythical: Boolean? = null,
    @SerialName("hatch_counter") var hatchCounter: Int? = null,
    @SerialName("has_gender_differences") var hasGenderDifferences: Boolean? = null,
    @SerialName("forms_switchable") var formsSwitchable: Boolean? = null,
    @SerialName("growth_rate") var growthRate: NameUrl? = NameUrl(),
    @SerialName("pokedex_numbers") var pokedexNumbers: List<PokedexNumbers> = emptyList(),
    @SerialName("egg_groups") var eggGroups: List<NameUrl> = emptyList(),
    @SerialName("color") var color: NameUrl? = NameUrl(),
    @SerialName("shape") var shape: NameUrl? = NameUrl(),
    @SerialName("evolves_from_species") var evolvesFromSpecies: NameUrl? = NameUrl(),
    @SerialName("evolution_chain") var evolutionChain: EvolutionChain? = EvolutionChain(),
    @SerialName("habitat") var habitat: String? = null,
    @SerialName("generation") var generation: NameUrl? = NameUrl(),
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("form_descriptions") var formDescriptions: List<FormDescriptions> = emptyList(),
    @SerialName("genera") var genera: List<Genera> = emptyList(),
    @SerialName("varieties") var varieties: List<Varieties> = emptyList()

)

