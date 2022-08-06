package com.project.pradyotprakash.rental.app.pages.information.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A view model class for the information screen
 */
@HiltViewModel
class InformationViewModel @Inject constructor() : ViewModel() {
    lateinit var userType: UserType
    var onlyPreview: Boolean = false

    /**
     * Set the initial value of the view model
     */
    fun start(userType: UserType, onlyPreview: Boolean) {
        this.userType = userType
        this.onlyPreview = onlyPreview
    }
}