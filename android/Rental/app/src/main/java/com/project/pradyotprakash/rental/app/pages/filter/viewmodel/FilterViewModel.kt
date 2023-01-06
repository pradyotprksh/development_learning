package com.project.pradyotprakash.rental.app.pages.filter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.filter.utils.FilterOptions
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

    fun updateBasicFieldState(index: Int, value: String = "") {
        _basicFields.value?.get(index)?.let { field ->
            field.value.value = value
            field.isSelected.value?.let {
                field.isSelected.value = !it
            }
        }
    }
}