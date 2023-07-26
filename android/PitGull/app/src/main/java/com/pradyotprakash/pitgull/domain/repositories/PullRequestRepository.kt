package com.pradyotprakash.pitgull.domain.repositories

import com.pradyotprakash.pitgull.core.response.parseResponse
import com.pradyotprakash.pitgull.data.services.PullRequestService
import com.pradyotprakash.pitgull.domain.model.PullRequestState

class PullRequestRepository(
    private val pullRequestService: PullRequestService
) {
    suspend fun getPullRequests(
        owner: String,
        repo: String,
        state: PullRequestState,
    ) = pullRequestService.getPullRequests(
        owner = owner,
        repo = repo,
        state = state.name,
    ).parseResponse()
}