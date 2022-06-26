package com.project.pradyotprakash.twitter.ui

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.twitter.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
/**
 * View model class for the [MainView], this will handle all the business logic
 * and helps in keeping the view class clean.
 *
 * @param appUtils An instance of [AppUtils] class.
 */
class MainViewModel @Inject constructor(
    private val appUtils: AppUtils,
): ViewModel()