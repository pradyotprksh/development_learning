package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Versions(

    @SerialName("generation-i") val generationi: GenerationI? = GenerationI(),
    @SerialName("generation-ii") val generationii: GenerationII? = GenerationII(),
    @SerialName("generation-iii") val generationiii: GenerationIII? = GenerationIII(),
    @SerialName("generation-iv") val generationiv: GenerationIV? = GenerationIV(),
    @SerialName("generation-v") val generationv: GenerationV? = GenerationV(),
    @SerialName("generation-vi") val generationvi: GenerationVI? = GenerationVI(),
    @SerialName("generation-vii") val generationvii: GenerationVII? = GenerationVII(),
    @SerialName("generation-viii") val generationviii: GenerationVIII? = GenerationVIII()

)