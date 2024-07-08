package app.pages.home.grok.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.grok.state.HomeGrokState
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.grok.GrokRepository
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeGrokViewModel(
    private val grokRepository: GrokRepository = SharedModulesDi.Instance.grokRepository,
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
) : ViewModel() {
    private val _homeGrokState = MutableStateFlow(HomeGrokState())
    val homeGrokState = _homeGrokState.asStateFlow()

    init {
        initialSetup()
    }

    private fun initialSetup() {
        listenToChatChanges()
        listenToCurrentUserDetails()
    }

    private fun listenToCurrentUserDetails() {
        viewModelScope.launch {
            currentUserRepository.getUserId()?.let { currentUserId ->
                currentUserRepository.userInfoChanges(currentUserId).collect { info ->
                    _homeGrokState.update {
                        it.copy(
                            profileImage = info?.profilePicture,
                        )
                    }
                }
            }
        }
    }

    private fun listenToChatChanges() {
        viewModelScope.launch {
            grokRepository.allGrokChangesChanges().collect { grokChats ->
                _homeGrokState.update {
                    it.copy(
                        grokChats = grokChats,
                    )
                }
            }
        }
    }

    fun removeSnackBarMessage() {
        _homeGrokState.update {
            it.copy(
                snackBarMessage = null,
            )
        }
    }

    fun updatePrompt(value: String) {
        _homeGrokState.update {
            it.copy(
                prompt = value,
            )
        }
    }

    fun sendPrompt() {
        val prompt = _homeGrokState.value.prompt
        val chatId = _homeGrokState.value.selectedChatId
        if (prompt.isNotBlank()) {
            viewModelScope.launch {
                grokRepository.sendPrompt(
                    prompt = prompt,
                    chatId = chatId,
                ).collect { response ->
                    when (response) {
                        is ClientResponse.Error -> showMessage(response.message)
                        ClientResponse.Idle -> updateLoaderState(false)
                        ClientResponse.Loading -> {
                            updatePrompt("")
                            updateLoaderState(true)
                        }

                        is ClientResponse.Success -> {
                            selectChat(response.data)
                        }
                    }
                }
            }
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeGrokState.update {
            it.copy(
                showLoading = showLoader,
            )
        }
    }

    private fun showMessage(message: String) {
        _homeGrokState.update {
            it.copy(
                snackBarMessage = message,
            )
        }
    }

    private fun selectChat(chatId: String) {
        _homeGrokState.update {
            it.copy(
                selectedChatId = chatId,
            )
        }
    }

    fun selectIndex(index: Int) {
        if (index > 0) {
            selectChat(_homeGrokState.value.grokChats[index - 1].chatId)
        } else {
            selectChat("")
        }
    }
}