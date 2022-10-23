package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.data.services.SearchService

class SearchRepository(
    private val searchService: SearchService,
    private val crashlytics: CrashlyticsService,
) {
    suspend fun performSearchQuery(
        searchedText: String,
    ) = searchService.performSearch(
        searchedText = searchedText,
    ).parseResponse(crashlytics)
}