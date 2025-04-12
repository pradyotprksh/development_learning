package com.pradyotprakash.unitconverter.app

import com.pradyotprakash.unitconverter.core.models.request.Units

data class UnitConverterState(
    val tabs: List<Units> = Units.entries,
)