package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

sealed interface DetailsState {
    object Idle: DetailsState

    object Loading: DetailsState

    data class Error(val message: String): DetailsState
}