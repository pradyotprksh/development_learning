package com.pradyotprakash.libraryowner.core.models

import com.google.gson.annotations.SerializedName

data class IpGeolocationDetails(

    @SerializedName("ip") val ip: String? = null,
    @SerializedName("continent_code") val continentCode: String? = null,
    @SerializedName("continent_name") val continentName: String? = null,
    @SerializedName("country_code2") val countryCode2: String? = null,
    @SerializedName("country_code3") val countryCode3: String? = null,
    @SerializedName("country_name") val countryName: String? = null,
    @SerializedName("country_name_official") val countryNameOfficial: String? = null,
    @SerializedName("country_capital") val countryCapital: String? = null,
    @SerializedName("state_prov") val stateProv: String? = null,
    @SerializedName("state_code") val stateCode: String? = null,
    @SerializedName("district") val district: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("zipcode") val zipcode: String? = null,
    @SerializedName("latitude") val latitude: String? = null,
    @SerializedName("longitude") val longitude: String? = null,
    @SerializedName("is_eu") val isEu: Boolean? = null,
    @SerializedName("calling_code") val callingCode: String? = null,
    @SerializedName("country_tld") val countryTld: String? = null,
    @SerializedName("languages") val languages: String? = null,
    @SerializedName("country_flag") val countryFlag: String? = null,
    @SerializedName("geoname_id") val geonameId: String? = null,
    @SerializedName("isp") val isp: String? = null,
    @SerializedName("connection_type") val connectionType: String? = null,
    @SerializedName("organization") val organization: String? = null,
    @SerializedName("currency") val currency: Currency? = Currency(),
    @SerializedName("time_zone") val timeZone: TimeZone? = TimeZone()

)

