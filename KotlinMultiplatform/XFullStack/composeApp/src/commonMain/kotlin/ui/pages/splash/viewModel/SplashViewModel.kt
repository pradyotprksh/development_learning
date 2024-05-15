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
        if (currentUserRepository.getCurrentLoggedInUserId() == null) {
            navigateToAuthOption()
            return
        }

        navigateToHome()
    }
}