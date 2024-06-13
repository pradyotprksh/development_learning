package app.pages.profileDetails.viewModel

import androidx.lifecycle.ViewModel
import app.pages.profileDetails.state.ProfileDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileDetailsViewModel : ViewModel() {
    private val _profileDetailsState = MutableStateFlow(ProfileDetailsState())
    val profileDetailsState = _profileDetailsState.asStateFlow()

    private fun updateLoaderState(showLoader: Boolean) {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            showLoading = showLoader
        )
    }

    private fun showMessage(message: String) {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            snackBarMessage = null
        )
    }
}