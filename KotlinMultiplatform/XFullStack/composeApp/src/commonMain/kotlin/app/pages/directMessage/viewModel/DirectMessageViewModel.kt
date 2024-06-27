package app.pages.directMessage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.directMessage.state.DirectMessageState
import app.pages.directMessage.state.MessageDetails
import core.models.response.ChatResponse
import core.models.response.ClientResponse
import core.models.response.MessageResponse
import core.models.response.UserInfoResponse
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
        chatId?.let {
            listenToChatChanges(chatId)
            listenToMessages(chatId)
            updateChatAndMessages(it)
        }
    }

    private fun updateChatAndMessages(chatId: String) {
        viewModelScope.launch {
            chatRepository.updateMessage(chatId).collect {
                when (it) {
                    is ClientResponse.Error -> showMessage(it.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {}
                }
            }
        }
    }

    private fun listenToChatChanges(chatId: String) {
        viewModelScope.launch {
            chatRepository.getChatChanges(chatId).collect {
                updateChatResponse(it)
            }
        }
    }

    private fun listenToMessages(chatId: String) {
        viewModelScope.launch {
            chatRepository.allMessagesChanges(chatId).collect {
                updateMessages(it)
            }
        }
    }

    private fun updateMessages(messageResponses: List<MessageResponse>) {
        val messageDetails = mutableListOf<MessageDetails>()
        var addGroup: Boolean
        var lastAdded = ""

        for (message in messageResponses) {
            addGroup = if (messageDetails.isEmpty()) {
                true
            } else {
                message.messageGroup != lastAdded
            }
            if (addGroup) {
                lastAdded = message.messageGroup
                messageDetails.add(
                    MessageDetails(
                        group = message.messageGroup,
                    )
                )
            }
            messageDetails.add(MessageDetails(messageResponse = message))
        }

        _directMessageStateState.update {
            it.copy(
                messages = messageDetails,
            )
        }
    }

    private fun updateChatResponse(chat: ChatResponse?) {
        if (chat != null) {
            _directMessageStateState.update {
                it.copy(
                    chatId = chat.chatId
                )
            }

            updateUserInfo(chat.users.filter { !it.isSameUser })
        }
    }

    private fun userInfoChanges(userId: String) {
        viewModelScope.launch {
            userRepository.getUserInfoChanges(userId).collect { userInfo ->
                userInfo?.let {
                    updateUserInfo(listOf(it))
                }
            }
        }
    }

    private fun updateUserInfo(usersInfo: List<UserInfoResponse>) {
        _directMessageStateState.update {
            it.copy(
                usersInfo = usersInfo,
            )
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