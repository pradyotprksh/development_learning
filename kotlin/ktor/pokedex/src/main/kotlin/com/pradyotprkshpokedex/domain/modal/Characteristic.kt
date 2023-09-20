package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Characteristic(

    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("gene_modulo") val geneModulo: Int? = null,
    @SerialName("highest_stat") val highestStat: NameUrl? = NameUrl(),
    @SerialName("id") val id: Int? = null,
    @SerialName("possible_values") val possibleValues: List<Int> = emptyList()

)
