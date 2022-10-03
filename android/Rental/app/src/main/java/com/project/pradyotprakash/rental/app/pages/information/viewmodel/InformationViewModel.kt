package com.project.pradyotprakash.rental.app.pages.information.viewmodel

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.utils.DateTransformation
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.app.utils.isAllNotNull
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldId
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.models.InputType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import com.project.pradyotprakash.rental.domain.services.AppCheckService
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A view model class for the information screen
 */
@HiltViewModel
class InformationViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val appCheckService: AppCheckService,
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
            appCheckService.getAppCheckToken().collect { appCheckToken ->
                when (appCheckToken) {
                    is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                    is RenterResponse.Idle -> _loading.value = false
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        authenticationUseCase.getCurrentUserId()?.let { userId ->
                            authenticationUseCase.getCurrentUserDetails(
                                userId = userId,
                                appCheckToken = appCheckToken.data,
                            ).collect {
                                when (it) {
                                    is RenterResponse.Error -> {
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
                                            authStateListener.stateChange(AuthState.Unauthenticated)
                                        }
                                    }
                                    is RenterResponse.Idle -> _loading.value = false
                                }
                            }
                        }
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
                    readOnly = onlyPreview,
                    composeType = ComposeType.OutlinedTextField,
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
                    readOnly = onlyPreview,
                    composeType = ComposeType.OutlinedTextField,
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
                    readOnly = onlyPreview,
                    composeType = ComposeType.OutlinedTextField,
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
                    ),
                    composeType = ComposeType.OutlinedTextField,
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
                    readOnly = onlyPreview,
                    composeType = ComposeType.OutlinedTextField,
                ),
                FieldStates(
                    id = FieldId.Address.id,
                    value = MutableLiveData(userDetails.permanent_address),
                    label = TR.permanentAddress,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    readOnly = onlyPreview,
                    composeType = ComposeType.OutlinedTextField,
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
                    ),
                    composeType = ComposeType.OutlinedTextField,
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
                fields.find { it.id == FieldId.Address.id }?.value?.value
            val emailAddress =
                fields.find { it.id == FieldId.EmailAddress.id }?.value?.value

            viewModelScope.launch {
                appCheckService.getAppCheckToken().collect { appCheckToken ->
                    when (appCheckToken) {
                        is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            isAllNotNull(
                                appCheckToken,
                                firstName,
                                lastName,
                                dateOfBirth,
                                profession,
                                phoneNumber,
                                permanentAddress,
                                emailAddress,
                                onNull = {
                                    updateErrorState(TR.dataMissing)
                                },
                                onNotNull = {
                                    initiateUpdateUserDetails(
                                        appCheckToken.data,
                                        firstName!!,
                                        lastName!!,
                                        dateOfBirth!!,
                                        profession!!,
                                        phoneNumber!!,
                                        permanentAddress!!,
                                        emailAddress!!,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initiateUpdateUserDetails(
        appCheckToken: String,
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        profession: String,
        phoneNumber: String,
        permanentAddress: String,
        emailAddress: String,
    ) {
        viewModelScope.launch {
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
                    appCheckToken = appCheckToken,
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

    fun updateFieldState(value: String = "", index: Int) {
        _fields.value?.get(index)?.let {
            it.value.value = value
        }
    }

    fun updateErrorState(errorText: String? = null) {
        _loading.value = false
        _errorText.value = errorText ?: ""
    }
}