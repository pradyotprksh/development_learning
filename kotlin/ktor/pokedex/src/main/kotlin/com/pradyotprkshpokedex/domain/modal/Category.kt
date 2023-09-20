package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("items") val items: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pocket") val pocket: NameUrl? = NameUrl()

)