package com.project.pradyotprakash.rental.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class for the [MainActivity], this will handle all the business logic
 * and helps in keeping the view class clean.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel()