package com.pradyotprakash.personalblog.app.pages.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.personalblog.app.pages.home.state.HomeState
import com.pradyotprakash.personalblog.core.models.response.ClientResponse
import com.pradyotprakash.personalblog.di.SharedModulesDi
import com.pradyotprakash.personalblog.domain.repositories.personalBlog.PersonalBlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val personalBlogRepository: PersonalBlogRepository = SharedModulesDi.Instance.personalBlogRepository,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchAllBlogs()
    }

    private fun fetchAllBlogs() {
        viewModelScope.launch {
            personalBlogRepository.fetchAllBlogs().collect { result ->
                when(result) {
                    is ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Error -> showMessage(result.message)
                    is ClientResponse.Idle -> updateLoaderState(false)
                    is ClientResponse.Success -> {
                        val blogs = result.data.data ?: emptyList()
                        _homeState.update {
                            it.copy(
                                blogs = blogs.sortedByDescending { it.publicationDate },
                                showLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }



    private fun showMessage(message: String) {
        _homeState.update {
            it.copy(
                errorMessage = message,
            )
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeState.update {
            it.copy(
                showLoading = showLoader,
            )
        }
    }
}