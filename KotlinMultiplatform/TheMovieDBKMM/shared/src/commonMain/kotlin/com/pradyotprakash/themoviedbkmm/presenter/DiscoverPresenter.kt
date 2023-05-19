package com.pradyotprakash.themoviedbkmm.presenter

import com.pradyotprakash.themoviedbkmm.core.exception.NoIdeaException
import com.pradyotprakash.themoviedbkmm.core.services.DiscoverService
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverMovies
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverTv

class DiscoverPresenter(
    private val discoverService: DiscoverService,
) {
    suspend fun getMovies(): DiscoverMovies {
        val result = discoverService.getMovies()
        if (result.isSuccess) {
            return result.getOrThrow()
        }
        throw result.exceptionOrNull() ?: NoIdeaException()
    }

    suspend fun getTvs(): DiscoverTv {
        val result = discoverService.getTv()
        if (result.isSuccess) {
            return result.getOrThrow()
        }
        throw result.exceptionOrNull() ?: NoIdeaException()
    }
}