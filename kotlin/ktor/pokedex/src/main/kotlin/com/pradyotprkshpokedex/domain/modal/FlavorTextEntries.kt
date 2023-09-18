package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntries(

    @SerialName("flavor_text") var flavorText: String? = null,
    @SerialName("language") var language: NameUrl? = NameUrl(),
    @SerialName("text") var text: String? = null,
    @SerialName("version_group") var versionGroup: VersionGroup? = VersionGroup(),
    @SerialName("version") var version: Version? = Version()

)