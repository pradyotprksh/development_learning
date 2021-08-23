package com.project.pradyotprakash.whatsappcompose.modules.chatUser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.whatsappcompose.models.ChatDetails
import com.project.pradyotprakash.whatsappcompose.models.MessageDetails
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreCallbacks
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreUtility
import com.project.pradyotprakash.whatsappcompose.utils.Utility

/**
 * A view model for ChatUserView which will be used to do all the business logic and
 * update the ui if required.
 */
class ChatUserViewModel : ViewModel() {
    private val firestoreUtility: FirestoreUtility = FirestoreUtility()

    var firstCallDone: Boolean = false

    /**
     * Message to be shown to the user.
     */
    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    /**
     * Is there is message to be shown to the user.
     */
    private val _showMessage = MutableLiveData(false)
    val showMessage: LiveData<Boolean> = _showMessage

    /**
     * Update show message and value when the snack bar is dismissed.
     *
     * This is required to let the compose know that the snackbar is being dismissed so when
     * next time the snackbar needs to be re-build it will do that instead of not re-building it
     * since the value has not changed.
     */
    fun snackbarDismissed() {
        _showMessage.value = false
        _message.value = ""
    }

    /**
     * Is a loader need to be shown to the user.
     */
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    /**
     * Chat history is present or not
     */
    private val _isChatHistoryPresent = MutableLiveData(false)
    val isChatHistoryPresent: LiveData<Boolean> = _isChatHistoryPresent

    /**
     * Current user details
     */
    private val _selectedUserDetails = MutableLiveData(User())
    val selectedUserDetails: LiveData<User> = _selectedUserDetails

    /**
     * Chat details
     */
    var currentChatDetails: ChatDetails = ChatDetails()

    /**
     * Check for user chat
     */
    fun checkForUserAndChat(userId: String) {
        _loading.value = true
        getSelectedUserDetails(userId)
    }

    /**
     * Get selected user details
     */
    private fun getSelectedUserDetails(userId: String) {
        firestoreUtility.getUserDetails(
            userId = userId,
            callbacks = object : FirestoreCallbacks {
                override fun onError(message: String) {
                    _loading.value = false
                    _showMessage.value = true
                    _message.value = message
                }

                override fun userDetails(user: User) {
                    _selectedUserDetails.value = user
                    isChatHistoryPresent(userId)
                }
            }
        )
    }

    /**
     * Check if the chat history is present between the current user and the [userId]
     */
    private fun isChatHistoryPresent(userId: String) {
        firestoreUtility.isChatAvailable(
            toUserId = userId,
            callbacks = object : FirestoreCallbacks {
                override fun onError(message: String) {
                    _loading.value = false
                    _showMessage.value = true
                    _message.value = message
                }

                override fun isFalse() {
                    _loading.value = false
                    _isChatHistoryPresent.value = false
                }

                override fun isTrue() {
                    _loading.value = false
                    _isChatHistoryPresent.value = true
                }

                override fun chatDetails(chatDetails: ChatDetails) {
                    currentChatDetails = chatDetails
                    updateMessageRead(userId)
                }
            }
        )
    }

    /**
     * Update the message read or not if the last message is not by current user
     */
    private fun updateMessageRead(userId: String) {
        if (!currentChatDetails.isLastMessageRead &&
            currentChatDetails.lastMessageSentBy?.id != firestoreUtility.getCurrentUserId()) {
            currentChatDetails.isLastMessageRead = true

            firestoreUtility.sendMessage(
                toUserId = userId,
                chatDetails = currentChatDetails,
                callbacks = object : FirestoreCallbacks {
                    override fun isTrue() {}

                    override fun onError(message: String) {
                        _showMessage.value = true
                        _message.value = message
                    }
                }
            )
        }
    }

    /**
     * Send message
     */
    fun sendMessage(message: String, sentTo: String, isFirstMessage: Boolean = false) {
        currentChatDetails.lastMessage = message
        currentChatDetails.lastMessageSentOn = Utility.currentTimeStamp()
        currentChatDetails.lastMessageSentBy = firestoreUtility.currentUserReference()
        if (isFirstMessage) {
            currentChatDetails.chatCreatedOn = Utility.currentTimeStamp()
            currentChatDetails.chatCreatedBy = firestoreUtility.currentUserReference()
            currentChatDetails.isChatFavourite = false
        }
        currentChatDetails.isLastMessageRead = false

        val messageDetails = MessageDetails(
            message = message,
            sentBy = firestoreUtility.currentUserReference(),
            sentOn = Utility.currentTimeStamp()
        )

        firestoreUtility.sendMessage(
            toUserId = sentTo,
            messageDetails = messageDetails,
            chatDetails = currentChatDetails,
            isFirstMessage = isFirstMessage,
            callbacks = object : FirestoreCallbacks {
                override fun isTrue() {}

                override fun onError(message: String) {
                    _showMessage.value = true
                    _message.value = message
                }
            }
        )
    }
}