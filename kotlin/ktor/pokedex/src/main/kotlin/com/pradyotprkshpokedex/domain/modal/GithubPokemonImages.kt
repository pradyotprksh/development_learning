package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubPokemonImages (

    @SerialName("name"         ) var name        : String? = null,
    @SerialName("path"         ) var path        : String? = null,
    @SerialName("sha"          ) var sha         : String? = null,
    @SerialName("size"         ) var size        : Int?    = null,
    @SerialName("url"          ) var url         : String? = null,
    @SerialName("html_url"     ) var htmlUrl     : String? = null,
    @SerialName("git_url"      ) var gitUrl      : String? = null,
    @SerialName("download_url" ) var downloadUrl : String? = null,
    @SerialName("type"         ) var type        : String? = null,
    @SerialName("_links"       ) var links       : Links?  = Links()

)

