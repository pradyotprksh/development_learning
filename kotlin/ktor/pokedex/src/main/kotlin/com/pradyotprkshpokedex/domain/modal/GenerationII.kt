package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationII(

    @SerialName("crystal") val crystal: Crystal? = Crystal(),
    @SerialName("gold") val gold: Gold? = Gold(),
    @SerialName("silver") val silver: Silver? = Silver()

)