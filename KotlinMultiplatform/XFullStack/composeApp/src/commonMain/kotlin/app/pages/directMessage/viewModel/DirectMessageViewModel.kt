package app.pages.directMessage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.directMessage.state.DirectMessageState
import di.SharedModulesDi
import domain.repositories.user.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DirectMessageViewModel(
    private val userRepository: UserRepository = SharedModulesDi.Instance.userRepository,
) : ViewModel() {
    private val _directMessageStateState = MutableStateFlow(DirectMessageState())
    val directMessageStateState = _directMessageStateState.asStateFlow()

    fun loadDetails(userId: String?, chatId: String?) {
        userId?.let { userInfoChanges(it) }
        chatId?.let { chatDetails(it) }
    }

    private fun chatDetails(chatId: String) {
        TODO("Not yet implemented")
    }

    private fun userInfoChanges(userId: String) {
        viewModelScope.launch {
            userRepository.getUserInfoChanges(userId).collect {
                _directMessageStateState.value = _directMessageStateState.value.copy(
                    userInfo = it,
                )
            }
        }
    }
}