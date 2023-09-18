package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedBlue(

    @SerialName("back_default") var backDefault: String? = null,
    @SerialName("back_gray") var backGray: String? = null,
    @SerialName("front_default") var frontDefault: String? = null,
    @SerialName("front_gray") var frontGray: String? = null

)