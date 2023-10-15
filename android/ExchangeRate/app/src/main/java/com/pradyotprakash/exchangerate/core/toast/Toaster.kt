package com.pradyotprakash.exchangerate.core.toast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Toaster @Inject constructor() {
    private val _toastActions = MutableLiveData<String>()
    val toastActions: LiveData<String>
        get() = _toastActions

    fun toast(message: String) {
        _toastActions.postValue(message)
    }
}