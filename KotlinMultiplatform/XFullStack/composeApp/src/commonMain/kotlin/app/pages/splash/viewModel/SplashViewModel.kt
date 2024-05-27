package app.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import app.pages.splash.state.SplashState
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.server.utils.ServerUtilsRepository
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import org.kodein.di.instance

class SplashViewModel : ViewModel() {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()
    private val serverUtilsRepository: ServerUtilsRepository by ModulesDi.di.instance()

    private val _splashScreenState = MutableStateFlow(SplashState())
    val splashScreenState = _splashScreenState.asStateFlow()

    suspend fun initiate(navigateToAuthOption: () -> Unit, navigateToHome: () -> Unit) {
        serverUtilsRepository.isServerAvailable().collect {
            when (it) {
                is ClientResponse.Error -> {
                    showMessage(message = it.message)
                }

                else -> {}
            }

            if (it is ClientResponse.Error || it is ClientResponse.Success) {
                checkForAuthentication(
                    navigateToAuthOption = navigateToAuthOption,
                    navigateToHome = navigateToHome,
                )
            }
        }
    }

    private suspend fun checkForAuthentication(
        navigateToAuthOption: () -> Unit,
        navigateToHome: () -> Unit
    ) {
        val currentUserId = currentUserRepository.getUserId()
        if (currentUserId == null) {
            navigateToAuthOption()
            return
        }

        checkCurrentUserAuthentication(
            navigateToAuthOption,
            navigateToHome,
        )
    }

    private suspend fun checkCurrentUserAuthentication(
        navigateToAuthOption: () -> Unit,
        navigateToHome: () -> Unit,
    ) {
        currentUserRepository.authenticateUser().collect {
            if (it is ClientResponse.Error || it is ClientResponse.Success) {
                updateCurrentUserInfo(
                    navigateToAuthOption,
                    navigateToHome,
                )
            }
        }
    }

    private suspend fun updateCurrentUserInfo(
        navigateToAuthOption: () -> Unit,
        navigateToHome: () -> Unit
    ) {
        currentUserRepository.updateUserInfo().collect {
            when (it) {
                is ClientResponse.Error -> {
                    currentUserRepository.getUserId()?.let { userId ->
                        currentUserRepository.userInfoChanges(userId).firstOrNull()?.let {
                            navigateToHome()
                        } ?: run {
                            deleteLocalDbAndRedirectToAuthOption(navigateToAuthOption)
                        }
                    } ?: run {
                        deleteLocalDbAndRedirectToAuthOption(navigateToAuthOption)
                    }
                }

                is ClientResponse.Success -> {
                    navigateToHome()
                }

                else -> {}
            }
        }
    }

    private suspend fun deleteLocalDbAndRedirectToAuthOption(navigateToAuthOption: () -> Unit) {
        currentUserRepository.deleteUserDetails(
            fromLocal = true,
            fromRemote = false
        )
        navigateToAuthOption()
    }

    private fun showMessage(message: String) {
        _splashScreenState.value = _splashScreenState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _splashScreenState.value = _splashScreenState.value.copy(
            snackBarMessage = null
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _splashScreenState.value = _splashScreenState.value.copy(
            showLoading = showLoader
        )
    }
}