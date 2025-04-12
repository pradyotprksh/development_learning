package com.pradyotprakash.unitconverter.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UnitConverterViewModel : ViewModel() {
    private val _unitConverterState = MutableStateFlow(UnitConverterState())
    val unitConverterStateState = _unitConverterState.asStateFlow()
}