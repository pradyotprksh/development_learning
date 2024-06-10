package app.composables.userAppBar.viewModel

import androidx.lifecycle.ViewModel
import app.composables.userAppBar.state.UserAppBarState
import di.SharedModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserAppBarViewModel(
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
) : ViewModel() {
    private val _userAppBarState = MutableStateFlow(UserAppBarState())
    val userAppBarState = _userAppBarState.asStateFlow()

    suspend fun initialSetup() {
        getCurrentUserInfo()
    }

    private suspend fun getCurrentUserInfo() {
        currentUserRepository.getUserId()?.let { currentUserId ->
            currentUserRepository.userInfoChanges(currentUserId).collect {
                if (it != null) {
                    _userAppBarState.value = _userAppBarState.value.copy(
                        profileImage = it.profilePicture,
                    )
                }
            }
        }
    }
}