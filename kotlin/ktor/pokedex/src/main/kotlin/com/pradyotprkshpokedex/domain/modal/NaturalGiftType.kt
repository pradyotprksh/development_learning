package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class NaturalGiftType(
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)