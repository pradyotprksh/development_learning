package com.pradyotprakash.themoviedbkmm.android.app.pages.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverMovies
import com.pradyotprakash.themoviedbkmm.data.models.DiscoverTv
import com.pradyotprakash.themoviedbkmm.di.DiFactory
import com.pradyotprakash.themoviedbkmm.presenter.DiscoverPresenter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DiscoverViewModel(
    private val discoverPresenter: DiscoverPresenter = DiFactory.discoverPresenter,
): ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _movies = MutableLiveData<DiscoverMovies>()
    val movies: LiveData<DiscoverMovies>
        get() = _movies
    private val _tvs = MutableLiveData<DiscoverTv>()
    val tvs: LiveData<DiscoverTv>
        get() = _tvs
    private val _movieError = MutableLiveData("")
    val movieError: LiveData<String>
        get() = _movieError
    private val _tvError = MutableLiveData("")
    val tvError: LiveData<String>
        get() = _tvError

    suspend fun getDiscoverDetails() {
        supervisorScope {
            _loading.value = true
            val moviesDeferred = async { discoverPresenter.getMovies() }
            val tvsDeferred = async { discoverPresenter.getTvs() }

            try {
                _movies.value = moviesDeferred.await()
            } catch (e: Exception) {
                _movieError.value = e.localizedMessage
            }

            try {
                _tvs.value = tvsDeferred.await()
            } catch (e: Exception) {
                _tvError.value = e.localizedMessage
            }

            _loading.value = false
        }
    }
}