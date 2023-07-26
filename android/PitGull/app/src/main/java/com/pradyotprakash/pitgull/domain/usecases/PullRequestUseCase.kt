package com.pradyotprakash.pitgull.domain.usecases

import com.pradyotprakash.pitgull.core.response.PitGullResponse
import com.pradyotprakash.pitgull.domain.model.PullRequestState
import com.pradyotprakash.pitgull.domain.repositories.PullRequestRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PullRequestUseCase @Inject constructor(
    private val pullRequestRepository: PullRequestRepository,
) {
    suspend fun getClosedPullRequests(
        owner: String,
        repo: String,
    ) = flow {
        emit(PitGullResponse.Loading)
        delay(2000)
        emit(
            pullRequestRepository.getPullRequests(
                owner = owner,
                repo = repo,
                state = PullRequestState.closed,
            )
        )
        emit(PitGullResponse.Idle)
    }
}