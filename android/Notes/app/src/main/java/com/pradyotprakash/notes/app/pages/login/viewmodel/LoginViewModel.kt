package com.pradyotprakash.notes.app.pages.login.viewmodel

import androidx.lifecycle.ViewModel
import com.pradyotprakash.notes.domain.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {
}