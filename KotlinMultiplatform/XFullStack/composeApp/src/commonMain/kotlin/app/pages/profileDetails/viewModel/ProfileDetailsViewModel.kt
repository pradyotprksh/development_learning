package app.pages.profileDetails.viewModel

import androidx.lifecycle.ViewModel
import app.pages.profileDetails.state.ProfileDetailsState
import di.SharedModulesDi
import domain.repositories.user.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileDetailsViewModel(
    private val userRepository: UserRepository = SharedModulesDi.Instance.userRepository,
) : ViewModel() {
    private val _profileDetailsState = MutableStateFlow(ProfileDetailsState())
    val profileDetailsState = _profileDetailsState.asStateFlow()

    suspend fun initialSetup(userId: String?) {
        userId?.let {
            userRepository.getUserInfoChanges(userId).collect {
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    userInfoResponse = it,
                )
            }
        }
    }

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