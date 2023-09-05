package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class Item(
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)