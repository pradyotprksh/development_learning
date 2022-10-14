package com.project.pradyotprakash.rental.app.utils

/**
 * A constant file for the app layer, which will contains all
 * the constants related to the application layer.
 */

object Constants {
    const val defaultLanguage = "en"
}

enum class UserType {
    Owner,
    Renter,
}

object WelcomeScreenArguments {
    const val userType = "userType"
}

object InformationScreenArguments {
    const val userType = "userType"
    const val onlyPreview = "onlyPreview"
    const val allowBackOption = "allowBackOption"
    const val firstTimeAddingDetails = "firstTimeAddingDetails"
}

object ErrorScreenArguments {
    const val title = "title"
    const val subtitle = "subtitle"
    const val description = "description"
}

object PropertyDetailsArguments {
    const val propertyId = "propertyId"
}