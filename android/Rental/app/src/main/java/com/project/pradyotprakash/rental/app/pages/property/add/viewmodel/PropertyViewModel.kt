package com.project.pradyotprakash.rental.app.pages.property.add.viewmodel

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.StorageReference
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.utils.isAllNotNull
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldId
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.FirestoreService
import com.project.pradyotprakash.rental.di.Constants
import com.project.pradyotprakash.rental.domain.modal.LocationEntity
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.PropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val navigator: Navigator,
    private val firestoreService: FirestoreService,
    private val authenticationUseCase: AuthenticationUseCase,
    private val propertyUseCase: PropertyUseCase,
    @Named(Constants.propertyStorageReference) private val propertyStorageReference: StorageReference,
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
    private val _propertyDetails = MutableLiveData<PropertyEntity?>(null)
    val propertyDetails: LiveData<PropertyEntity?>
        get() = _propertyDetails

    private fun updateFieldDetails() {
        val propertyDetails = _propertyDetails.value

        val fields = listOf(
            FieldStates(
                id = FieldId.PropertyName.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.propertyName,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                value = MutableLiveData(propertyDetails?.property_name)
            ),
            FieldStates(
                id = FieldId.Address.id,
                label = TR.address,
                composeType = ComposeType.LocationPicker,
                locationDetails = MutableLiveData(propertyDetails?.address)
            ),
            FieldStates(
                id = FieldId.PropertyType.id,
                label = TR.typeOfProperty,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                composeType = ComposeType.OutlinedTextField,
                value = MutableLiveData(propertyDetails?.property_type)
            ),
            FieldStates(
                id = FieldId.BathroomNumber.id,
                label = TR.numberOfBathrooms,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                composeType = ComposeType.OutlinedTextField,
                value = MutableLiveData(propertyDetails?.number_of_bathrooms)
            ),
            FieldStates(
                id = FieldId.Deposit.id,
                label = TR.deposit,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                composeType = ComposeType.OutlinedTextField,
                value = MutableLiveData(propertyDetails?.yearly_deposit)
            ),
            FieldStates(
                id = FieldId.MonthlyRent.id,
                label = TR.rent,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                composeType = ComposeType.OutlinedTextField,
                value = MutableLiveData(propertyDetails?.monthly_rent)
            ),
            FieldStates(
                id = FieldId.Perks.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.propertyPerks,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                value = MutableLiveData(propertyDetails?.perks)
            ),
            FieldStates(
                id = FieldId.AgreementRules.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.propertyAgreements,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        createRentalProperty()
                    }
                ),
                value = MutableLiveData(propertyDetails?.agreement_rules)
            ),
            FieldStates(
                id = FieldId.PropertyImagePicker.id,
                composeType = ComposeType.MultipleImagePicker,
                label = TR.propertyImages,
                storageReference = propertyStorageReference,
                values = MutableLiveData(propertyDetails?.property_images ?: emptyList())
            ),
            FieldStates(
                id = FieldId.WhereItIs.id,
                composeType = ComposeType.RadioGroup,
                label = TR.wherePropertyIs,
                value = MutableLiveData(propertyDetails?.where_it_is ?: FieldId.House.id),
                children = listOf(
                    FieldStates(
                        id = FieldId.Society.id,
                        label = TR.inSociety,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.Apartment.id,
                        label = TR.inApartment,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.House.id,
                        label = TR.inHouse,
                        composeType = ComposeType.RadioButton,
                    ),
                ),
            ),
            FieldStates(
                id = FieldId.IsRentalOwner.id,
                composeType = ComposeType.RadioGroup,
                label = TR.areYouOwner,
                value = MutableLiveData(propertyDetails?.is_rental_owner ?: FieldId.True.id),
                children = listOf(
                    FieldStates(
                        id = FieldId.True.id,
                        label = TR.yes,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.False.id,
                        label = TR.no,
                        composeType = ComposeType.RadioButton,
                    )
                ),
            ),
            FieldStates(
                id = FieldId.IsForRental.id,
                composeType = ComposeType.Switch,
                label = TR.isForRentalWithQuestionMark,
                isSelected = MutableLiveData(propertyDetails?.isForRental ?: true),
            ),
            FieldStates(
                id = FieldId.PropertyFor.id,
                composeType = ComposeType.RadioGroup,
                label = TR.kindOfRentalAllowed,
                value = MutableLiveData(propertyDetails?.property_for ?: FieldId.DoesNotMatter.id),
                children = listOf(
                    FieldStates(
                        id = FieldId.ForFamily.id,
                        label = TR.onlyFamilies,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.ForBachelors.id,
                        label = TR.onlyBachelors,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.DoesNotMatter.id,
                        label = TR.doesNotMatter,
                        composeType = ComposeType.RadioButton,
                    ),
                ),
            ),
            FieldStates(
                id = FieldId.FurnishedType.id,
                composeType = ComposeType.RadioGroup,
                label = TR.furnishedType,
                value = MutableLiveData(propertyDetails?.furnished_type ?: FieldId.None.id),
                children = listOf(
                    FieldStates(
                        id = FieldId.FullyFurnished.id,
                        label = TR.fullyFurnished,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.SemiFurnished.id,
                        label = TR.semiFurnished,
                        composeType = ComposeType.RadioButton,
                    ),
                    FieldStates(
                        id = FieldId.None.id,
                        label = TR.none,
                        composeType = ComposeType.RadioButton,
                    ),
                ),
            ),
        )

        _fields.value = fields
    }

    fun createRentalProperty() {
        _fields.value?.let { fields ->
            val propertyId = firestoreService.getRandomDocumentId()
            val name = fields.find { it.id == FieldId.PropertyName.id }?.value?.value
            val address = fields.find { it.id == FieldId.Address.id }?.locationDetails?.value
            val areYouTheOwner =
                fields.find { it.id == FieldId.IsRentalOwner.id }?.value?.value
            val isForRental =
                fields.find { it.id == FieldId.IsForRental.id }?.isSelected?.value
            val kindOfRenter =
                fields.find { it.id == FieldId.PropertyFor.id }?.value?.value
            val furnishedType =
                fields.find { it.id == FieldId.FurnishedType.id }?.value?.value
            val propertyType =
                fields.find { it.id == FieldId.PropertyType.id }?.value?.value
            val numberOfBathroom =
                fields.find { it.id == FieldId.BathroomNumber.id }?.value?.value
            val wherePropertyIs =
                fields.find { it.id == FieldId.WhereItIs.id }?.value?.value
            val depositAmount = fields.find { it.id == FieldId.Deposit.id }?.value?.value
            val rentAmount = fields.find { it.id == FieldId.MonthlyRent.id }?.value?.value
            val perks = fields.find { it.id == FieldId.Perks.id }?.value?.value
            val agreementTerms =
                fields.find { it.id == FieldId.AgreementRules.id }?.value?.value
            val images = fields.find { it.id == FieldId.PropertyImagePicker.id }?.values?.value
                ?: emptyList()

            isAllNotNull(
                propertyId,
                name,
                address,
                areYouTheOwner,
                isForRental,
                kindOfRenter,
                furnishedType,
                propertyType,
                numberOfBathroom,
                wherePropertyIs,
                depositAmount,
                rentAmount,
                perks,
                agreementTerms,
                onNull = {
                    updateErrorState(TR.dataMissing)
                },
                onNotNull = {
                    initiateAddPropertyDetails(
                        propertyId,
                        name!!,
                        address!!,
                        areYouTheOwner!!,
                        isForRental!!,
                        kindOfRenter!!,
                        furnishedType!!,
                        propertyType!!,
                        numberOfBathroom!!,
                        wherePropertyIs!!,
                        depositAmount!!,
                        rentAmount!!,
                        perks!!,
                        agreementTerms!!,
                        images,
                    )
                },
            )
        }
    }

    private fun initiateAddPropertyDetails(
        propertyId: String,
        name: String,
        address: LocationEntity,
        areYouTheOwner: String,
        forRental: Boolean,
        kindOfRenter: String,
        furnishedType: String,
        propertyType: String,
        numberOfBathroom: String,
        wherePropertyIs: String,
        depositAmount: String,
        rentAmount: String,
        perks: String,
        agreementTerms: String,
        images: List<String>,
    ) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                propertyUseCase.createProperty(
                    userId = userId,
                    propertyId = propertyId,
                    name = name,
                    address = address,
                    areYouTheOwner = areYouTheOwner,
                    forRental = forRental,
                    kindOfRenter = kindOfRenter,
                    furnishedType = furnishedType,
                    propertyType = propertyType,
                    numberOfBathroom = numberOfBathroom,
                    wherePropertyIs = wherePropertyIs,
                    depositAmount = depositAmount,
                    rentAmount = rentAmount,
                    perks = perks,
                    agreementTerms = agreementTerms,
                    images = images,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> navigateBack()
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

    fun updateFieldState(index: Int, value: String = "") {
        _fields.value?.get(index)?.let { field ->
            field.value.value = value
            field.isSelected.value?.let {
                field.isSelected.value = !it
            }
        }
    }

    fun updateErrorState(errorText: String? = null) {
        _errorText.value = errorText ?: ""
    }

    fun setInitialScreenDetails(propertyId: String) {
        if (propertyId.isEmpty()) {
            updateFieldDetails()
        } else {
            fetchPropertyDetails(propertyId)
        }
    }

    private fun fetchPropertyDetails(propertyId: String) {
        viewModelScope.launch {
            propertyUseCase.getProperties(
                propertyId = propertyId
            ).collect {
                when (it) {
                    is RenterResponse.Error -> updateErrorState(it.exception.message)
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        val data = it.data.data
                        data?.firstOrNull()?.let { propertyDetails ->
                            _propertyDetails.value = propertyDetails
                            updateFieldDetails()
                        }
                    }
                    is RenterResponse.Idle -> _loading.value = false
                }
            }
        }
    }
}