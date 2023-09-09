package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Berry(
    @SerialName("firmness") val firmness: NameUrl? = NameUrl(),
    @SerialName("flavors") val flavors: List<Flavors> = emptyList(),
    @SerialName("growth_time") val growthTime: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("item") val item: NameUrl? = NameUrl(),
    @SerialName("max_harvest") val maxHarvest: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("natural_gift_power") val naturalGiftPower: Int? = null,
    @SerialName("natural_gift_type") val naturalGiftType: NameUrl? = NameUrl(),
    @SerialName("size") val size: Int? = null,
    @SerialName("smoothness") val smoothness: Int? = null,
    @SerialName("soil_dryness") val soilDryness: Int? = null,
    @SerialName("image") var image: String? = name?.let { "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/berries/$name-berry.png" }
)