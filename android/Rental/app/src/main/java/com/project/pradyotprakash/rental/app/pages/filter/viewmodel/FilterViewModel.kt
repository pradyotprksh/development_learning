package com.project.pradyotprakash.rental.app.pages.filter.viewmodel

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.filter.utils.FilterOptions
import com.project.pradyotprakash.rental.app.utils.DateTransformation
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldId
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.usecase.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val localServices: UserLocalServices,
    private val filterUseCase: FilterUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _optionSelected = MutableLiveData(FilterOptions.values().first())
    val optionSelected: LiveData<FilterOptions>
        get() = _optionSelected
    private val _basicFields = MutableLiveData(emptyList<FieldStates>())
    val basicFields: LiveData<List<FieldStates>>
        get() = _basicFields
    private val _textFields = MutableLiveData(emptyList<FieldStates>())
    val textFields: LiveData<List<FieldStates>>
        get() = _textFields
    private val _moneyFields = MutableLiveData(emptyList<FieldStates>())
    val moneyFields: LiveData<List<FieldStates>>
        get() = _moneyFields
    private val _timelineFields = MutableLiveData(emptyList<FieldStates>())
    val timelineFields: LiveData<List<FieldStates>>
        get() = _timelineFields
    private val _locationFields = MutableLiveData(emptyList<FieldStates>())
    val locationFields: LiveData<List<FieldStates>>
        get() = _locationFields
    private val _properties = MutableLiveData<List<PropertyEntity>>()
    val properties: LiveData<List<PropertyEntity>>
        get() = _properties

    private val savedLatitude: String
        get() = localServices.currentLocation.first
    private val savedLongitude: String
        get() = localServices.currentLocation.second

    init {
        setupItems()
    }

    private fun setupItems() {
        _textFields.value = listOf(
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
            ),
            FieldStates(
                id = FieldId.OwnerName.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.ownerName,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
            ),
            FieldStates(
                id = FieldId.Address.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.address,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
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
            ),
        )

        _basicFields.value = listOf(
            FieldStates(
                id = FieldId.BathroomNumber.id,
                label = TR.numberOfBathrooms,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                composeType = ComposeType.OutlinedTextField,
            ),
            FieldStates(
                id = FieldId.WhereItIs.id,
                composeType = ComposeType.RadioGroup,
                label = TR.wherePropertyIs,
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
                label = TR.isListedByOwner,
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
                isSelected = MutableLiveData(false),
            ),
            FieldStates(
                id = FieldId.PropertyFor.id,
                composeType = ComposeType.RadioGroup,
                label = TR.kindOfRentalAllowed,
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

        _moneyFields.value = listOf(
            FieldStates(
                id = FieldId.DepositRangeLabel.id,
                composeType = ComposeType.Label,
                label = TR.depositRange,
            ),
            FieldStates(
                id = FieldId.DepositRangeStart.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.depositStart,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            ),
            FieldStates(
                id = FieldId.DepositRangeEnd.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.depositEnd,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
            ),
            FieldStates(
                id = FieldId.Divider.id,
                composeType = ComposeType.Divider,
            ),
            FieldStates(
                id = FieldId.RentRangeLabel.id,
                composeType = ComposeType.Label,
                label = TR.rentRange,
            ),
            FieldStates(
                id = FieldId.RentRangeStart.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.rentStart,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            ),
            FieldStates(
                id = FieldId.RentRangeEnd.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.rentEnd,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
            ),
        )

        _timelineFields.value = listOf(
            FieldStates(
                id = FieldId.UpdatedOnLabel.id,
                composeType = ComposeType.Label,
                label = TR.updatedOnRange,
            ),
            FieldStates(
                id = FieldId.UpdatedOnRangeStart.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.updatedOnRangeStart,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                visualTransformation = DateTransformation(),
            ),
            FieldStates(
                id = FieldId.UpdatedOnRangeEnd.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.updatedOnRangeEnd,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = DateTransformation(),
            ),
        )

        _locationFields.value = listOf(
            FieldStates(
                id = FieldId.DistanceRangeLabel.id,
                composeType = ComposeType.Label,
                label = TR.distanceRange,
            ),
            FieldStates(
                id = FieldId.DistanceRangeStart.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.distanceRangeStart,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            ),
            FieldStates(
                id = FieldId.DistanceRangeEnd.id,
                composeType = ComposeType.OutlinedTextField,
                label = TR.distanceRangeEnd,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
            ),
        )
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun onOptionSelected(selectedOption: FilterOptions) {
        _optionSelected.value = selectedOption
    }

    fun clearFilterSheetState() {}

    fun updateBasicFieldState(index: Int, value: String = "", option: FilterOptions) {
        when (option) {
            FilterOptions.Text -> {
                _textFields.value?.get(index)?.let { field ->
                    field.value.value = value
                    field.isSelected.value?.let {
                        field.isSelected.value = !it
                    }
                }
            }
            FilterOptions.Basic -> {
                _basicFields.value?.get(index)?.let { field ->
                    field.value.value = value
                    field.isSelected.value?.let {
                        field.isSelected.value = !it
                    }
                }
            }
            FilterOptions.Money -> {
                _moneyFields.value?.get(index)?.let { field ->
                    field.value.value = value
                    field.isSelected.value?.let {
                        field.isSelected.value = !it
                    }
                }
            }
            FilterOptions.Timeline -> {
                _timelineFields.value?.get(index)?.let { field ->
                    field.value.value = value
                    field.isSelected.value?.let {
                        field.isSelected.value = !it
                    }
                }
            }
            FilterOptions.Location -> {
                _locationFields.value?.get(index)?.let { field ->
                    field.value.value = value
                    field.isSelected.value?.let {
                        field.isSelected.value = !it
                    }
                }
            }
        }
    }

    fun setFilters() {
        val fields = (_basicFields.value ?: emptyList()) + (_textFields.value
            ?: emptyList()) + (_moneyFields.value ?: emptyList()) + (_timelineFields.value
            ?: emptyList()) + (_locationFields.value ?: emptyList())

        val propertyName = fields.find { it.id == FieldId.PropertyName.id }?.value?.value?.ifBlank { null }
        val ownerName = fields.find { it.id == FieldId.OwnerName.id }?.value?.value?.ifBlank { null }
        val address = fields.find { it.id == FieldId.Address.id }?.value?.value?.ifBlank { null }
        val propertyType = fields.find { it.id == FieldId.PropertyType.id }?.value?.value?.ifBlank { null }

        val whereIsIt = fields.find { it.id == FieldId.WhereItIs.id }?.value?.value?.ifBlank { null }
        val listedByOwner = fields.find { it.id == FieldId.IsRentalOwner.id }?.value?.value?.ifBlank { null }
        val isForRental = fields.find { it.id == FieldId.IsForRental.id }?.isSelected?.value
        val kindOfRenter = fields.find { it.id == FieldId.PropertyFor.id }?.value?.value?.ifBlank { null }
        val furnishedType = fields.find { it.id == FieldId.FurnishedType.id }?.value?.value?.ifBlank { null }
        val numberOfBathrooms = fields.find { it.id == FieldId.BathroomNumber.id }?.value?.value?.ifBlank { null }

        val depositStart = fields.find { it.id == FieldId.DepositRangeStart.id }?.value?.value?.ifBlank { null }
        val depositEnd = fields.find { it.id == FieldId.DepositRangeEnd.id }?.value?.value?.ifBlank { null }
        val rentStart = fields.find { it.id == FieldId.RentRangeStart.id }?.value?.value?.ifBlank { null }
        val rentEnd = fields.find { it.id == FieldId.RentRangeEnd.id }?.value?.value?.ifBlank { null }

        val updatedOnStart = fields.find { it.id == FieldId.UpdatedOnRangeStart.id }?.value?.value?.ifBlank { null }
        val updatedOnEnd = fields.find { it.id == FieldId.UpdatedOnRangeEnd.id }?.value?.value?.ifBlank { null }

        val distanceStart = fields.find { it.id == FieldId.DistanceRangeStart.id }?.value?.value?.ifBlank { null }
        val distanceEnd = fields.find { it.id == FieldId.DistanceRangeEnd.id }?.value?.value?.ifBlank { null }

        callFilterService(
            propertyName,
            ownerName,
            address,
            whereIsIt,
            propertyType,
            listedByOwner,
            isForRental?.toString(),
            kindOfRenter,
            furnishedType,
            depositStart,
            depositEnd,
            rentStart,
            rentEnd,
            updatedOnStart,
            updatedOnEnd,
            distanceStart,
            distanceEnd,
            savedLatitude,
            savedLongitude,
            numberOfBathrooms,
        )
    }

    private fun callFilterService(
        propertyName: String?,
        ownerName: String?,
        address: String?,
        whereIsIt: String?,
        propertyType: String?,
        listedByOwner: String?,
        forRental: String?,
        kindOfRenter: String?,
        furnishedType: String?,
        depositStart: String?,
        depositEnd: String?,
        rentStart: String?,
        rentEnd: String?,
        updatedOnStart: String?,
        updatedOnEnd: String?,
        distanceStart: String?,
        distanceEnd: String?,
        savedLatitude: String,
        savedLongitude: String,
        numberOfBathrooms: String?,
    ) {
        viewModelScope.launch {
            filterUseCase.performFilterQuery(
                property_name = propertyName,
                owner_name = ownerName,
                property_address = address,
                listed_by_owner = listedByOwner,
                for_rental = forRental,
                property_for = kindOfRenter,
                furnished_type = furnishedType,
                property_type = propertyType,
                where_it_is = whereIsIt,
                number_of_bathrooms = numberOfBathrooms,
                yearly_deposit_start = depositStart,
                yearly_deposit_end = depositEnd,
                monthly_rent_start = rentStart,
                monthly_rent_end = rentEnd,
                property_updated_on_start = updatedOnStart,
                property_updated_on_end = updatedOnEnd,
                distance_start = distanceStart,
                distance_end = distanceEnd,
                user_latitude = savedLatitude,
                user_longitude = savedLongitude,
            ).collect {
                when (it) {
                    is RenterResponse.Error -> updateErrorState(it.exception.message)
                    RenterResponse.Idle -> _loading.value = false
                    RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> _properties.value = it.data.data ?: emptyList()
                }
            }
        }
    }

    fun clearFilters() {
        setupItems()
    }

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }
}