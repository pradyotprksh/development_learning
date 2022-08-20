package com.project.pradyotprakash.rental.app.pages.information.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class InputType {
    Text,
    Date,
    Email,
    Phone,
}

data class FieldStates(
    var value: String = "",
    val label: String,
    val inputType: InputType = InputType.Text,
    val editable: Boolean = true,
)

/**
 * A view model class for the information screen
 */
@HiltViewModel
class InformationViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    private val _fields = MutableLiveData(emptyList<FieldStates>())
    val fields: LiveData<List<FieldStates>>
        get() = _fields

    init {
        val fields = listOf(
            FieldStates(
                value = "",
                label = "First Name",
            ),
            FieldStates(
                value = "",
                label = "Last Name",
            ),
            FieldStates(
                value = "",
                label = "Date of Birth",
                inputType = InputType.Date,
            ),
            FieldStates(
                value = "",
                label = "Email Address",
                inputType = InputType.Email,
                editable = false,
            ),
            FieldStates(
                value = "",
                label = "Profession",
            ),
            FieldStates(
                value = "",
                label = "Phone Number",
                inputType = InputType.Phone,
            ),
        )

        _fields.value = fields
    }

    lateinit var userType: UserType
    private var onlyPreview: Boolean = false

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    /**
     * Set the initial value of the view model
     */
    fun start(userType: UserType, onlyPreview: Boolean) {
        this.userType = userType
        this.onlyPreview = onlyPreview
    }

    fun updateFieldState(index: Int, value: String = "") {
        val fieldsList = _fields.value
        fieldsList?.get(index)?.also {
            it.value = value
        }
        _fields.value = fieldsList
    }
}