package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Berry(
    @SerialName("firmness") var firmness: Firmness? = null,
    @SerialName("flavors") var flavors: List<Flavors> = emptyList(),
    @SerialName("growth_time") var growthTime: Int? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("item") var item: Item? = null,
    @SerialName("max_harvest") var maxHarvest: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("natural_gift_power") var naturalGiftPower: Int? = null,
    @SerialName("natural_gift_type") var naturalGiftType: NaturalGiftType? = null,
    @SerialName("size") var size: Int? = null,
    @SerialName("smoothness") var smoothness: Int? = null,
    @SerialName("soil_dryness") var soilDryness: Int? = null
)