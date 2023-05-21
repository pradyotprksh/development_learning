package com.pradyotprakash.findingfalcone.domain.entity

data class FindEntity(
    val planet_name: String?,
    val status: String?,
    val error: String?,
) {
    val isFound: Boolean
        get() = status == "success"
}
