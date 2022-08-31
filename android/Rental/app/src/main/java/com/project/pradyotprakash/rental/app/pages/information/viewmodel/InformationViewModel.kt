package com.project.pradyotprakash.rental.app.pages.information.viewmodel

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.utils.DateTransformation
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class FieldId(val id: String) {
    None("none"),
    FirstName("first_name"),
    LastName("last_name"),
    DOB("date_of_birth"),
    EmailAddress("email_address"),
    Profession("profession"),
    PhoneNumber("phone_number"),
    PermanentAddress("permanent_address")
}

enum class InputType {
    Text,
    Date,
    Email,
    Phone,
}

data class FieldStates(
    val id: String = FieldId.None.id,
    var value: MutableLiveData<String>,
    val label: String,
    val inputType: InputType = InputType.Text,
    val readOnly: Boolean = false,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val maxChar: Int = -1,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
)

/**
 * A view model class for the information screen
 */
@HiltViewModel
class InformationViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
) : ViewModel() {
    lateinit var userType: UserType
    private var onlyPreview: Boolean = false
    var allowBackOption: Boolean = false

    private val _fields = MutableLiveData(emptyList<FieldStates>())
    val fields: LiveData<List<FieldStates>>
        get() = _fields

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    init {
        updateFieldDetails(authStateListener.userDetails.value)
    }

    private fun checkForUserDetails() {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                _loading.value = true
                authenticationUseCase.getCurrentUserDetails(userId = userId).collect {
                    when (it) {
                        is RenterResponse.Error -> {
                            authenticationUseCase.logoutUser()
                            authStateListener.stateChange(AuthState.Unauthenticated)
                        }
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            it.data.data?.let { userDetails ->
                                authStateListener.updateUserDetails(userDetails)
                                if (userDetails.is_all_details_available) {
                                    navigateBack()
                                } else {
                                    updateFieldDetails(userDetails)
                                }
                            } ?: kotlin.run {
                                authenticationUseCase.logoutUser()
                                authStateListener.stateChange(AuthState.Unauthenticated)
                            }
                        }
                        is RenterResponse.Idle -> _loading.value = false
                    }
                }
            }
        }
    }

    private fun updateFieldDetails(userDetails: UserEntity?) {
        userDetails?.let {
            userType = UserType.valueOf(userDetails.user_type)
            val fields = listOf(
                FieldStates(
                    id = FieldId.FirstName.id,
                    value = MutableLiveData(userDetails.first_name),
                    label = TR.firstName,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    readOnly = onlyPreview
                ),
                FieldStates(
                    id = FieldId.LastName.id,
                    value = MutableLiveData(userDetails.last_name),
                    label = TR.lastName,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    readOnly = onlyPreview
                ),
                FieldStates(
                    id = FieldId.DOB.id,
                    value = MutableLiveData(userDetails.date_of_birth),
                    label = TR.dobWithHelp,
                    inputType = InputType.Date,
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    visualTransformation = DateTransformation(),
                    maxChar = 8,
                    readOnly = onlyPreview
                ),
                FieldStates(
                    id = FieldId.EmailAddress.id,
                    value = MutableLiveData(userDetails.email_address),
                    label = TR.emailAddress,
                    inputType = InputType.Email,
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    )
                ),
                FieldStates(
                    id = FieldId.Profession.id,
                    value = MutableLiveData(userDetails.profession),
                    label = TR.profession,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    readOnly = onlyPreview
                ),
                FieldStates(
                    id = FieldId.PermanentAddress.id,
                    value = MutableLiveData(userDetails.permanent_address),
                    label = TR.permanentAddress,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    readOnly = onlyPreview
                ),
                FieldStates(
                    id = FieldId.PhoneNumber.id,
                    value = MutableLiveData(userDetails.phone_number),
                    label = TR.phoneNumber,
                    inputType = InputType.Phone,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done,
                    ),
                    readOnly = onlyPreview,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            updateUserDetails()
                        }
                    )
                ),
            )

            _fields.value = fields
        }
    }

    fun updateUserDetails() {
        _fields.value?.let { fields ->
            val firstName = fields.find { it.id == FieldId.FirstName.id }?.value?.value
            val lastName = fields.find { it.id == FieldId.LastName.id }?.value?.value
            val dateOfBirth = fields.find { it.id == FieldId.DOB.id }?.value?.value
            val profession = fields.find { it.id == FieldId.Profession.id }?.value?.value
            val phoneNumber = fields.find { it.id == FieldId.PhoneNumber.id }?.value?.value
            val permanentAddress =
                fields.find { it.id == FieldId.PermanentAddress.id }?.value?.value
            val emailAddress =
                fields.find { it.id == FieldId.EmailAddress.id }?.value?.value

            firstName?.let {
                lastName?.let {
                    dateOfBirth?.let {
                        profession?.let {
                            phoneNumber?.let {
                                permanentAddress?.let {
                                    emailAddress?.let {
                                        initiateUpdateUserDetails(
                                            firstName,
                                            lastName,
                                            dateOfBirth,
                                            profession,
                                            phoneNumber,
                                            permanentAddress,
                                            emailAddress,
                                        )
                                    }
                                        ?: kotlin.run { updateErrorState(TR.dataMissing) }
                                } ?: kotlin.run { updateErrorState(TR.dataMissing) }
                            } ?: kotlin.run { updateErrorState(TR.dataMissing) }
                        } ?: kotlin.run { updateErrorState(TR.dataMissing) }
                    } ?: kotlin.run { updateErrorState(TR.dataMissing) }
                } ?: kotlin.run { updateErrorState(TR.dataMissing) }
            } ?: kotlin.run { updateErrorState(TR.dataMissing) }
        }
    }

    private fun initiateUpdateUserDetails(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        profession: String,
        phoneNumber: String,
        permanentAddress: String,
        emailAddress: String,
    ) {
        viewModelScope.launch {
            _loading.value = true
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                authenticationUseCase.updateUserDetails(
                    userId = userId,
                    userType = userType,
                    firstName = firstName,
                    lastName = lastName,
                    permanentAddress = permanentAddress,
                    dateOfBirth = dateOfBirth,
                    profession = profession,
                    phoneNumber = phoneNumber,
                    emailAddress = emailAddress,
                    isAllDetailsAvailable = true,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> checkForUserDetails()
                        is RenterResponse.Idle -> _loading.value = false
                    }
                }
            }
        }
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    /**
     * Set the initial value of the view model
     */
    fun start(userType: UserType, onlyPreview: Boolean, allowBackOption: Boolean) {
        this.userType = userType
        this.onlyPreview = onlyPreview
        this.allowBackOption = allowBackOption
    }

    fun updateFieldState(index: Int, value: String = "") {
        _fields.value?.get(index)?.let {
            it.value.value = value
        }
    }

    fun updateErrorState(errorText: String? = null) {
        _errorText.value = errorText ?: ""
    }
}