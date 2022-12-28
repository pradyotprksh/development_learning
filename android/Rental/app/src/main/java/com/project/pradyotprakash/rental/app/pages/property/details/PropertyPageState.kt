package com.project.pradyotprakash.rental.app.pages.property.details

sealed interface PropertyPageState {
    object Normal: PropertyPageState
    object Proposals: PropertyPageState
    object CreateProposal: PropertyPageState
}