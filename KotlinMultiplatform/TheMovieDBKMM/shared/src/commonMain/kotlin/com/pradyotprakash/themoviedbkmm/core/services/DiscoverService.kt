package com.pradyotprakash.themoviedbkmm.core.services

import com.pradyotprakash.themoviedbkmm.data.models.DiscoverMovies
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverTv

interface DiscoverService {
    suspend fun getMovies(): Result<DiscoverMovies>

    suspend fun getTv(): Result<DiscoverTv>
}