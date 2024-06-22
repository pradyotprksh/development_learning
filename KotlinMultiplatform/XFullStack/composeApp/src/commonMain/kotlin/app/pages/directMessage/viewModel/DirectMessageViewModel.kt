package app.pages.directMessage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.directMessage.state.DirectMessageState
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.chat.ChatRepository
import domain.repositories.user.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DirectMessageViewModel(
    private val userRepository: UserRepository = SharedModulesDi.Instance.userRepository,
    private val chatRepository: ChatRepository = SharedModulesDi.Instance.chatRepository,
) : ViewModel() {
    private val _directMessageStateState = MutableStateFlow(DirectMessageState())
    val directMessageStateState = _directMessageStateState.asStateFlow()

    fun loadDetails(userId: String?, chatId: String?) {
        userId?.let { userInfoChanges(it) }
        chatId?.let { chatDetails(it) }
    }

    private fun chatDetails(chatId: String) {
        viewModelScope.launch {
            chatRepository.getMessages(chatId).collect {
                when (it) {
                    is ClientResponse.Error -> showMessage(it.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {}
                }
            }
        }
    }

    private fun userInfoChanges(userId: String) {
        viewModelScope.launch {
            userRepository.getUserInfoChanges(userId).collect { userInfo ->
                _directMessageStateState.update {
                    it.copy(
                        userInfo = userInfo,
                    )
                }
            }
        }
    }

    private fun showMessage(message: String) {
        _directMessageStateState.update {
            it.copy(
                snackBarMessage = message,
            )
        }
    }

    fun removeSnackBarMessage() {
        _directMessageStateState.update {
            it.copy(
                snackBarMessage = null
            )
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _directMessageStateState.update {
            it.copy(
                showLoading = showLoader,
            )
        }
    }
}