package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Berry(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("growth_time") val growthTime: Int? = null,
    @SerialName("max_harvest") val maxHarvest: Int? = null,
    @SerialName("natural_gift_power") val naturalGiftPower: Int? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("smoothness") val smoothness: Int? = null,
    @SerialName("soil_dryness") val soilDryness: Int? = null,
    @SerialName("firmness") val firmness: NameUrl? = NameUrl(),
    @SerialName("flavors") val flavors: List<Flavors> = emptyList(),
    @SerialName("item") val item: NameUrl? = NameUrl(),
    @SerialName("natural_gift_type") val naturalGiftType: NameUrl? = NameUrl()

)

