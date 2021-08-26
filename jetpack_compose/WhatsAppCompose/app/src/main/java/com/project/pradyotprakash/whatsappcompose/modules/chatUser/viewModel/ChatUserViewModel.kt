package com.project.pradyotprakash.whatsappcompose.modules.chatUser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.whatsappcompose.models.ChatDetails
import com.project.pradyotprakash.whatsappcompose.models.ChatDetailsFirestore
import com.project.pradyotprakash.whatsappcompose.models.MessageDetailsFirestore
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
    var currentChatDetailsFirestore: ChatDetailsFirestore = ChatDetailsFirestore()

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

                override fun chatDetails(chatDetails: ChatDetails, chatDetailsFirestore: ChatDetailsFirestore) {
                    currentChatDetails = chatDetails
                    currentChatDetailsFirestore = chatDetailsFirestore
                    updateMessageRead(userId)
                }
            }
        )
    }

    /**
     * Update the message read or not if the last message is not by current user
     */
    private fun updateMessageRead(userId: String) {
        if (!currentChatDetailsFirestore.chatLastMessageRead &&
            currentChatDetailsFirestore.lastMessageSentBy?.id != firestoreUtility.getCurrentUserId()
        ) {
            currentChatDetailsFirestore.chatLastMessageRead = true

            firestoreUtility.sendPersonalMessage(
                toUserId = userId,
                chatDetailsFirestore = currentChatDetailsFirestore,
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
        currentChatDetailsFirestore.lastMessage = message
        currentChatDetailsFirestore.lastMessageSentOn = Utility.currentTimeStamp()
        currentChatDetailsFirestore.lastMessageSentBy = firestoreUtility.currentUserReference()
        if (isFirstMessage) {
            currentChatDetailsFirestore.chatCreatedOn = Utility.currentTimeStamp()
            currentChatDetailsFirestore.chatCreatedBy = firestoreUtility.currentUserReference()
            currentChatDetailsFirestore.chatLastMessageRead = false
            currentChatDetailsFirestore.chatIsAGroup = false
            currentChatDetailsFirestore.members = listOf(
                firestoreUtility.currentUserReference(),
                firestoreUtility.getUserReference(userId = sentTo)
            )
        }
        currentChatDetailsFirestore.chatLastMessageRead = false

        val messageDetails = MessageDetailsFirestore(
            message = message,
            sentBy = firestoreUtility.currentUserReference(),
            sentOn = Utility.currentTimeStamp()
        )

        firestoreUtility.sendPersonalMessage(
            toUserId = sentTo,
            messageDetailsFirestore = messageDetails,
            chatDetailsFirestore = currentChatDetailsFirestore,
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