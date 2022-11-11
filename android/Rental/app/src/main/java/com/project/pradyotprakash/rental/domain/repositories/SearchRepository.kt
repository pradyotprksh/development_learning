package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.data.services.LocationService
import com.project.pradyotprakash.rental.data.services.SearchService

class SearchRepository(
    private val searchService: SearchService,
    private val locationService: LocationService,
    private val crashlytics: CrashlyticsService,
) {
    suspend fun performSearchQuery(
        searchedText: String,
        userType: String,
    ) = searchService.performSearch(
        searchedText = searchedText,
        userType = userType,
    ).parseResponse(crashlytics)

    suspend fun performLocationSearch(
        searchedText: String,
        zipCode: String,
        latitude: String,
        longitude: String,
        exactly_one: Boolean,
    ) = locationService.searchLocation(
        searchedText = searchedText,
        zipCode = zipCode,
        latitude = latitude,
        longitude = longitude,
        exactly_one = exactly_one,
    ).parseResponse(crashlytics)
}