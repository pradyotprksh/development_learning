package com.project.pradyotprakash.rental.core.models

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.MutableLiveData

enum class FieldId(val id: String) {
    None("none"),
    True("True"),
    False("False"),
    Address("address");

    enum class User(val id: String) {
        FirstName("first_name"),
        LastName("last_name"),
        DOB("date_of_birth"),
        EmailAddress("email_address"),
        Profession("profession"),
        PhoneNumber("phone_number"),
    }

    enum class Property(val id: String) {
        PropertyName("property_name"),
        IsRentalOwner("is_rental_owner")
    }
}

enum class InputType {
    Text,
    Date,
    Email,
    Phone,
}

enum class ComposeType {
    None,
    OutlinedTextField,
    RadioGroup,
    RadioButton,
}

data class FieldStates(
    val id: String = FieldId.None.id,
    val composeType: ComposeType = ComposeType.None,
    var value: MutableLiveData<String> = MutableLiveData(""),
    val label: String = "",
    val inputType: InputType = InputType.Text,
    val readOnly: Boolean = false,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val maxChar: Int = -1,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val isSelected: Boolean = false,
    val children: List<FieldStates> = emptyList(),
)