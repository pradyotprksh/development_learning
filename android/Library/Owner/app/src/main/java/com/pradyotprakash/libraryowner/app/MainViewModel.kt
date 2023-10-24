package com.pradyotprakash.libraryowner.app

import androidx.lifecycle.ViewModel
import com.pradyotprakash.libraryowner.domain.usecases.AppConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigUseCase: AppConfigUseCase,
) : ViewModel() {
    val currentLanguage = appConfigUseCase.getCurrentLanguage()
}