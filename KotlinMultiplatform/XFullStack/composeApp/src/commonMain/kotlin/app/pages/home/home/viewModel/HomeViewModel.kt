package app.pages.home.home.viewModel

import androidx.lifecycle.ViewModel
import app.pages.home.home.state.HomeState
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.instance

class HomeViewModel : ViewModel() {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

    private val _homeScreenState = MutableStateFlow(HomeState())
    val homeScreenState = _homeScreenState.asStateFlow()

    private fun showMessage(message: String) {
        _homeScreenState.value = _homeScreenState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _homeScreenState.value = _homeScreenState.value.copy(
            snackBarMessage = null
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeScreenState.value = _homeScreenState.value.copy(
            showLoading = showLoader
        )
    }

    suspend fun initialSetup() {
        getCurrentUserInfo()
    }

    private suspend fun getCurrentUserInfo() {
        currentUserRepository.getUserId()?.let { currentUserId ->
            currentUserRepository.userInfoChanges(currentUserId).collect {
                if (it != null) {
                    _homeScreenState.value = _homeScreenState.value.copy(
                        profileImage = it.profilePicture,
                        name = it.name,
                        username = it.username,
                        following = it.following,
                        followers = it.followers,
                    )
                }
            }
        }
    }
}