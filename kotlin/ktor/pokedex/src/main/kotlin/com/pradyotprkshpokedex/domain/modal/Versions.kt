package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Versions(

    @SerialName("generation-i") var generationi: GenerationI? = GenerationI(),
    @SerialName("generation-ii") var generationii: GenerationII? = GenerationII(),
    @SerialName("generation-iii") var generationiii: GenerationIII? = GenerationIII(),
    @SerialName("generation-iv") var generationiv: GenerationIV? = GenerationIV(),
    @SerialName("generation-v") var generationv: GenerationV? = GenerationV(),
    @SerialName("generation-vi") var generationvi: GenerationVI? = GenerationVI(),
    @SerialName("generation-vii") var generationvii: GenerationVII? = GenerationVII(),
    @SerialName("generation-viii") var generationviii: GenerationVIII? = GenerationVIII()

)