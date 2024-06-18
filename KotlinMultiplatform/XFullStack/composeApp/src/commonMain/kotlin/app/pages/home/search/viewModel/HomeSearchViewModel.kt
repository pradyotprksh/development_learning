package app.pages.home.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.search.state.HomeSearchState
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.tags.TagsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeSearchViewModel(
    private val tagsRepository: TagsRepository = SharedModulesDi.Instance.tagsRepository,
) : ViewModel() {
    private val _homeSearchState = MutableStateFlow(HomeSearchState())
    val homeSearchState = _homeSearchState.asStateFlow()

    init {
        updateTrendingTags()
        listenToTrendingTagsChanges()
    }

    private fun showMessage(message: String) {
        _homeSearchState.value = _homeSearchState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _homeSearchState.value = _homeSearchState.value.copy(
            snackBarMessage = null
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeSearchState.value = _homeSearchState.value.copy(
            showLoading = showLoader
        )
    }

    private fun updateTrendingTags() {
        viewModelScope.launch {
            tagsRepository.updateTrendingTags().collect {
                when (it) {
                    is ClientResponse.Error -> showMessage(it.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {}
                }
            }
        }
    }

    private fun listenToTrendingTagsChanges() {
        viewModelScope.launch {
            tagsRepository.allTrendingTagsChanges(limit = 30).collect {
                _homeSearchState.value = _homeSearchState.value.copy(
                    trendingTags = it
                )
            }
        }
    }
}