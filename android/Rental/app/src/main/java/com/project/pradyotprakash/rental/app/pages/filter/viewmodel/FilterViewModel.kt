package com.project.pradyotprakash.rental.app.pages.filter.viewmodel

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.filter.utils.FilterOptions
import com.project.pradyotprakash.rental.app.utils.DateTransformation
import com.project.pradyotprakash.rental.core.models.ComposeType
import com.project.pradyotprakash.rental.core.models.FieldId
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
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

    init {
        setupItems()
    }

    private fun setupItems() {
        _basicFields.value = listOf(
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

    fun clearFilterSheetState() {
        _optionSelected.value = FilterOptions.values().first()
    }

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
}