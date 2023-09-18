package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Characteristic(

    @SerialName("descriptions") var descriptions: List<Descriptions> = emptyList(),
    @SerialName("gene_modulo") var geneModulo: Int? = null,
    @SerialName("highest_stat") var highestStat: NameUrl? = NameUrl(),
    @SerialName("id") var id: Int? = null,
    @SerialName("possible_values") var possibleValues: List<Int> = emptyList()

)
