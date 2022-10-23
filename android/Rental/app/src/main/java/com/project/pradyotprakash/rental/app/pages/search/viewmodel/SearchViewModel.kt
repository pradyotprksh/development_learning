package com.project.pradyotprakash.rental.app.pages.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.SearchEntity
import com.project.pradyotprakash.rental.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String>
        get() = _searchText
    private val _searchResult = MutableLiveData<SearchEntity?>(null)
    val searchResult: LiveData<SearchEntity?>
        get() = _searchResult

    private var job: Job? = null

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun updateSearchText(newValue: String) {
        _searchText.value = newValue
        _loading.value = newValue.isNotBlank()
        restartApiCall()
    }

    private fun restartApiCall() {
        job?.cancel()
        job = viewModelScope.launch {
            delay(3000)
            callForSearchApi()
        }
    }

    private suspend fun callForSearchApi() {
        _searchText.value?.let { searchedText ->
            searchUseCase.performSearchQuery(
                searchedText = searchedText,
            ).collect {
                when (it) {
                    is RenterResponse.Error -> updateErrorState(it.exception.message)
                    is RenterResponse.Idle -> _loading.value = false
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        _searchResult.value = it.data.data
                    }
                }
            }
        }
    }

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }

    fun goToUserDetails(userId: String) {
        navigator.navigate {
            it.navigate("${Routes.UserDetails.route}${userId}")
        }
    }
}