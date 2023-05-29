package com.pradyotprakash.themoviedbkmm.data.models

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json    = Json { allowStructuredMapKeys = true }
// val welcome = json.parse(Welcome.serializer(), jsonString)

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@Serializable
data class DiscoverTv(
    val page: Long,
    val results: List<TvResult>,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("total_results")
    val totalResults: Long
)

@Serializable
data class TvResult(
    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("first_air_date")
    val firstAirDate: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    val id: Long,
    val name: String,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
) {
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w200/$posterPath"
}
