package com.pradyotprakash.postscomments.core.toast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastListener @Inject constructor() {
    private val _toastAction = MutableLiveData<String>()
    val toastAction: LiveData<String>
        get() = _toastAction

    fun showToast(message: String) {
        _toastAction.postValue(message)
    }
}