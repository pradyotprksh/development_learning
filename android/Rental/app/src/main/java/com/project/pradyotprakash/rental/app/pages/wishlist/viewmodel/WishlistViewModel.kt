package com.project.pradyotprakash.rental.app.pages.wishlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.WishlistEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val navigator: Navigator,
    private val wishlistUseCase: WishlistUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _wishlists = MutableLiveData<List<WishlistEntity>>()
    val wishlists: LiveData<List<WishlistEntity>>
        get() = _wishlists

    fun getAllWishlist() {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                wishlistUseCase.getWishlist(
                    userId = userId
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            _wishlists.value = it.data.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }
}