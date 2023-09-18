package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationII(

    @SerialName("crystal") var crystal: Crystal? = Crystal(),
    @SerialName("gold") var gold: Gold? = Gold(),
    @SerialName("silver") var silver: Silver? = Silver()

)