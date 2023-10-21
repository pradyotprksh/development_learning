package com.pradyotprakash.libraryowner.app.pages.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.domain.usecases.UnsplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val unsplashUseCase: UnsplashUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _backgroundImageUrl = MutableLiveData("")
    val backgroundImageUrl: LiveData<String>
        get() = _backgroundImageUrl

    init {
        getBackgroundImage()
    }

    fun getBackgroundImage() {
        viewModelScope.launch {
            unsplashUseCase.getLibraryPortraitImage(count = 1).collect { backgroundImage ->
                when (backgroundImage) {
                    is OwnerResponse.Error -> updateErrorState(backgroundImage.exception.message)
                    is OwnerResponse.Idle -> _loading.value = false
                    is OwnerResponse.Loading -> _loading.value = true
                    is OwnerResponse.Success -> {
                        _backgroundImageUrl.value = backgroundImage.data.results
                            .firstOrNull()?.urls?.raw ?: ""
                    }
                }
            }
        }
    }

    private fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }
}