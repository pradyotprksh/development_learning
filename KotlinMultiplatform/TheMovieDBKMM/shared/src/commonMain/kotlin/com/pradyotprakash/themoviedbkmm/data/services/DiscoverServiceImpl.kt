package com.pradyotprakash.themoviedbkmm.data.services

import com.pradyotprakash.themoviedbkmm.core.models.RequestDetails
import com.pradyotprakash.themoviedbkmm.core.network.NetworkClient
import com.pradyotprakash.themoviedbkmm.core.services.DiscoverService
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverMovies
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverTv

class DiscoverServiceImpl(
    private val networkClient: NetworkClient,
) : DiscoverService {
    override suspend fun getMovies(): Result<DiscoverMovies> {
        return networkClient.get(
            details = RequestDetails(
                endpoint = "discover/movie"
            )
        )
    }

    override suspend fun getTv(): Result<DiscoverTv> {
        return networkClient.get(
            details = RequestDetails(
                endpoint = "discover/tv"
            )
        )
    }
}