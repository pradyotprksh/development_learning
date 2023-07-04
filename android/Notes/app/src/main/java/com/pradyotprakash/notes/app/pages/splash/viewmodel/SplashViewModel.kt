package com.pradyotprakash.notes.app.pages.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun updateErrorState() {
        _errorText.value = ""
    }
}