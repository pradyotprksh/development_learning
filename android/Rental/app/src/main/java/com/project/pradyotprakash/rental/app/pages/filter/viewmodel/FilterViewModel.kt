package com.project.pradyotprakash.rental.app.pages.filter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.pages.filter.utils.FilterOptions
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
}