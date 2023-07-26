package com.pradyotprakash.pitgull.app.pages.pullrequests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.pitgull.core.response.PitGullResponse
import com.pradyotprakash.pitgull.core.utils.Constants
import com.pradyotprakash.pitgull.domain.model.PullRequest
import com.pradyotprakash.pitgull.domain.usecases.PullRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestsViewModel @Inject constructor(
    private val pullRequestUseCase: PullRequestUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _closedPr = MutableLiveData<List<PullRequest>>()
    val closedPr: LiveData<List<PullRequest>>
        get() = _closedPr

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    init {
        getClosedPullRequests()
    }

    fun getClosedPullRequests() {
        viewModelScope.launch {
            pullRequestUseCase.getClosedPullRequests(
                owner = Constants.OWNER_NAME,
                repo = Constants.REPO,
            ).collect { response ->
                when (response) {
                    is PitGullResponse.Error -> updateErrorState(response.exception.localizedMessage)
                    is PitGullResponse.Loading -> _loading.value = true
                    is PitGullResponse.Idle -> _loading.value = false
                    is PitGullResponse.Success -> _closedPr.value = response.data.toList()
                        .sortedByDescending { it.closed_at }
                }
            }
        }
    }
}