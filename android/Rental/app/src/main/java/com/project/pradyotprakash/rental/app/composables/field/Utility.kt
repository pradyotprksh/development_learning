package com.project.pradyotprakash.rental.app.composables.field

import com.project.pradyotprakash.rental.core.models.FieldStates

sealed class FieldPageState {
    object Normal : FieldPageState()
    data class Location(val fieldStates: FieldStates) : FieldPageState()
}