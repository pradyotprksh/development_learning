package app.composables.userDrawer.viewModel

import androidx.lifecycle.ViewModel
import app.composables.userDrawer.state.UserDrawerState
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.instance

class UserDrawerViewModel : ViewModel() {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

    private val _userDrawerState = MutableStateFlow(UserDrawerState())
    val userDrawerState = _userDrawerState.asStateFlow()

    suspend fun initialSetup() {
        getCurrentUserInfo()
    }

    private suspend fun getCurrentUserInfo() {
        currentUserRepository.getUserId()?.let { currentUserId ->
            currentUserRepository.userInfoChanges(currentUserId).collect {
                if (it != null) {
                    _userDrawerState.value = _userDrawerState.value.copy(
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