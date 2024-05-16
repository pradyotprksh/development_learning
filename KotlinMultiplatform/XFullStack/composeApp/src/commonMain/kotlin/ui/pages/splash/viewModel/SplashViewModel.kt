package ui.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import di.ModulesDi
import domain.repositories.CurrentUserRepository
import kotlinx.coroutines.delay
import org.kodein.di.instance

class SplashViewModel : ViewModel() {
    private val currentUserRepository by ModulesDi.di.instance<CurrentUserRepository>()

    suspend fun navigateToAuthOption(
        navigateToAuthOption: () -> Unit,
        navigateToHome: () -> Unit
    ) {
        delay(3000)
        val currentUserId = currentUserRepository.getCurrentUserId()
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
    }
}