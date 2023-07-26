package com.pradyotprakash.pitgull.domain.model

data class PullRequest(
    var url: String? = null,
    var id: Int? = null,
    var title: String? = null,
    var user: User? = User(),
    var body: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var closed_at: String? = null,
)
