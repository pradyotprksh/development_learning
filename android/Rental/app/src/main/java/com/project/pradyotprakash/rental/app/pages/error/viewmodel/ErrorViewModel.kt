package com.project.pradyotprakash.rental.app.pages.error.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A view model class for the error screen
 */
@HiltViewModel
class ErrorViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    lateinit var title: String
    lateinit var subtitle: String
    lateinit var description: String

    /**
     * Set the initial value of the view model
     */
    fun start(title: String, subtitle: String, description: String) {
        this.title = title.ifEmpty { TR.defaultErrorTitle }
        this.subtitle = subtitle.ifEmpty { TR.defaultErrorSubTitle }
        this.description = description.ifEmpty { TR.defaultErrorDescription }
    }

    /**
     * Navigate back
     */
    fun navigateBack() {
        navigator.navigateBack()
        navigator.navigateBack()
    }
}