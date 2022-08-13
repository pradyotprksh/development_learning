package com.project.pradyotprakash.rental.app.pages.welcome.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class AuthType {
    Email,
    Phone,
    Google,
}

/**
 * The view model class which will handle the business logic.
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    lateinit var userType: UserType

    /**
     * Set the initial value of the view model
     */
    fun start(userType: UserType) {
        this.userType = userType
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun initiateAuthCall(authType: AuthType) {
        when (authType) {
            AuthType.Email -> {
                authenticationUseCase.createUserWithEmailPassword(
                    "",
                    ""
                ) {
                    
                }
            }
            AuthType.Phone -> {

            }
            AuthType.Google -> {

            }
        }
    }

    /**
     * Go to the get information details screen
     */
    fun goToInformationScreen() {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${userType}/${false}")
        }
    }
}