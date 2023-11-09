package com.pradyotprakash.libraryowner.core.models

import com.google.gson.annotations.SerializedName

data class TimeZone(

    @SerializedName("name") val name: String? = null,
    @SerializedName("offset") val offset: Double? = null,
    @SerializedName("offset_with_dst") val offsetWithDst: Double? = null,
    @SerializedName("current_time") val currentTime: String? = null,
    @SerializedName("current_time_unix") val currentTimeUnix: Double? = null,
    @SerializedName("is_dst") val isDst: Boolean? = null,
    @SerializedName("dst_savings") val dstSavings: Int? = null

)