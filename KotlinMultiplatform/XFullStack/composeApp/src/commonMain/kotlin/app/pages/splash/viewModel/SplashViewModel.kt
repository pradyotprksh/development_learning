package app.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import app.pages.splash.state.SplashState
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.server.utils.ServerUtilsRepository
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                    updateLoaderState(showLoader = false)
                    showMessage(message = it.message)
                }

                is ClientResponse.Success -> {
                    checkForAuthentication(
                        navigateToAuthOption = navigateToAuthOption,
                        navigateToHome = navigateToHome,
                    )
                }

                else -> {}
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
            when (it) {
                is ClientResponse.Error -> {
                    currentUserRepository.deleteUserDetails(fromLocal = true, fromRemote = false)
                    navigateToAuthOption()
                }

                is ClientResponse.Success -> {
                    updateCurrentUserInfo(
                        navigateToAuthOption,
                        navigateToHome,
                    )
                }

                else -> {}
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
                    currentUserRepository.deleteUserDetails(fromLocal = true, fromRemote = false)
                    navigateToAuthOption()
                }

                is ClientResponse.Success -> {
                    navigateToHome()
                }

                else -> {}
            }
        }
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