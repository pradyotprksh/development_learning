package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(

    @SerialName("id") var id: Int? = null,
    @SerialName("items") var items: List<Pagination> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pocket") var pocket: NameUrl? = NameUrl()

)