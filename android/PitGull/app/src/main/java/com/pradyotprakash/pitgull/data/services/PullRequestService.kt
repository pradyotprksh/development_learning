package com.pradyotprakash.pitgull.data.services

import com.pradyotprakash.pitgull.domain.model.PullRequest
import com.pradyotprakash.pitgull.domain.model.PullRequestState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestService {
    @GET("/repos/{owner_name}/{repo_name}/pulls")
    suspend fun getPullRequests(
        @Path("owner_name") owner: String,
        @Path("repo_name") repo: String,
        @Query("state") state: String = PullRequestState.all.name,
    ): Response<List<PullRequest>>
}