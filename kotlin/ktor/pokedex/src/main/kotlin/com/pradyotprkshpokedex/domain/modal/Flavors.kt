package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class Flavors(
    @SerialName("flavor") var flavor: Flavor? = Flavor(),
    @SerialName("potency") var potency: Int? = null
)