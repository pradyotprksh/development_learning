package app.pages.home.message.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.message.state.HomeMessageState
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.chat.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeMessageViewModel(
    private val chatRepository: ChatRepository = SharedModulesDi.Instance.chatRepository,
) : ViewModel() {
    private val _homeMessageState = MutableStateFlow(HomeMessageState())
    val homeMessageState = _homeMessageState.asStateFlow()

    init {
        initialSetup()
    }

    private fun initialSetup() {
        listToChats()
        updateChats()
    }

    private fun listToChats() {
        viewModelScope.launch {
            chatRepository.allChatsChanges().collect { chats ->
                _homeMessageState.update {
                    it.copy(
                        chats = chats,
                    )
                }
            }
        }
    }

    private fun updateChats() {
        viewModelScope.launch {
            chatRepository.updateChats().collect { response ->
                when (response) {
                    is ClientResponse.Error -> showMessage(response.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {}
                }
            }
        }
    }

    private fun showMessage(message: String) {
        _homeMessageState.update {
            it.copy(
                snackBarMessage = message,
            )
        }
    }

    fun removeSnackBarMessage() {
        _homeMessageState.update {
            it.copy(
                snackBarMessage = null
            )
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeMessageState.update {
            it.copy(
                showLoading = showLoader,
            )
        }
    }
}