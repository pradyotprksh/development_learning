package com.project.pradyotprakash.rental.app.pages.property.viewmodel

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldId
import com.project.pradyotprakash.rental.core.models.FieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _fields = MutableLiveData(emptyList<FieldStates>())
    val fields: LiveData<List<FieldStates>>
        get() = _fields

    init {
        updateFieldDetails()
    }

    private fun updateFieldDetails() {
        /*
        Set of details required for the property
        1. Property Name
        2. Address
        3. Owner Details - Is it your property?
        4. Is for rental
        5. Property for
            1. Family
            2. Bachelors
            3. Doesn't matter
        6. Number of people allowed
        7. Furnished type
            1. Fully
            2. Semi
            3. None
        8. Property type - 1BHK, 2BHK, 1RK, etc.
        9. Number of bathrooms
        10. Is it in a society/apartment/house?
        11. Perks
        12. Deposit
        13. Rent
        14. Is the money part negotiable?
        15. Agreement rules - Like how many years for the rent, rent increment
         */
        val fields = listOf(
            FieldStates(
                id = FieldId.Property.PropertyName.id,
                composeType = ComposeType.OutlinedTextField,
                label = "Property Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
            ),
            FieldStates(
                id = FieldId.Address.id,
                label = "Address",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                composeType = ComposeType.OutlinedTextField,
            ),
            FieldStates(
                id = FieldId.Property.IsRentalOwner.id,
                composeType = ComposeType.RadioGroup,
                label = "Are you the owner?",
                value = MutableLiveData(FieldId.True.id),
                children = listOf(
                    FieldStates(
                        id = FieldId.True.id,
                        label = "Yes",
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.False.id,
                        label = "No",
                        composeType = ComposeType.RadioButton,
                    )
                ),
            )
        )

        _fields.value = fields
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateFieldState(value: String = "", index: Int) {
        _fields.value?.get(index)?.let {
            it.value.value = value
        }
    }

    fun updateErrorState(errorText: String? = null) {
        _errorText.value = errorText ?: ""
    }
}