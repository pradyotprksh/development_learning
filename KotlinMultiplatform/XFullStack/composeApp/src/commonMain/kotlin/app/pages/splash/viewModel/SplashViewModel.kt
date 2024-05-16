package app.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.delay
import org.kodein.di.instance

class SplashViewModel : ViewModel() {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

    suspend fun navigateToAuthOption(
        navigateToAuthOption: () -> Unit,
        navigateToHome: () -> Unit
    ) {
        delay(3000)
        val currentUserId = currentUserRepository.getUserId()
        if (currentUserId == null) {
            navigateToAuthOption()
            return
        }

        checkCurrentUserAuthentication(
            navigateToAuthOption,
            navigateToHome
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

                is ClientResponse.Success -> navigateToHome()
                else -> {}
            }
        }
    }
}