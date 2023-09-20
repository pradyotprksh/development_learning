package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectChanges(
    @SerialName("effect_entries") val effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("version_group") val versionGroup: VersionGroup? = VersionGroup()

)