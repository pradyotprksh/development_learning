package com.project.pradyotprakash.whatsappcompose.modules.edit_profile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreUtility

/**
 * A view model for the EditProfileView which will do all the business logic and update the
 * ui state as per the requirement.
 */
class EditProfileViewModel : ViewModel() {
    private val database: FirestoreUtility = FirestoreUtility()

    /**
     * Current user details
     */
    private val _userDetails = MutableLiveData(User())
    val userDetails: LiveData<User> = _userDetails

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
     * Get user details and update the UI
     */
    fun getUserDetails() {
        _userDetails.value = database.getCurrentUserDetails()
    }
}