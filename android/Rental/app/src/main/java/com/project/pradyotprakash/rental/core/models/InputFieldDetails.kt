package com.project.pradyotprakash.rental.core.models

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.MutableLiveData

enum class FieldId(val id: String) {
    None("none"),
    True("True"),
    False("False"),
    Society("society"),
    Apartment("apartment"),
    House("house"),
    Deposit("yearly_deposit"),
    MonthlyRent("monthly_rent"),
    Address("address"),
    PropertyName("property_name"),
    IsRentalOwner("is_rental_owner"),
    IsForRental("is_for_rental"),
    PropertyFor("property_for"),
    ForFamily("for_family"),
    ForBachelors("for_bachelors"),
    DoesNotMatter("does_not_matter"),
    FurnishedType("furnished_type"),
    FullyFurnished("fully_furnished"),
    SemiFurnished("semi_furnished"),
    PropertyType("property_type"),
    BathroomNumber("number_of_bathrooms"),
    WhereItIs("where_it_is"),
    Perks("perks"),
    AgreementRules("agreement_rules"),
    FirstName("first_name"),
    LastName("last_name"),
    DOB("date_of_birth"),
    EmailAddress("email_address"),
    Profession("profession"),
    PhoneNumber("phone_number"),
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
    Switch,
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
    val isSelected: MutableLiveData<Boolean> = MutableLiveData(false),
    val children: List<FieldStates> = emptyList(),
)