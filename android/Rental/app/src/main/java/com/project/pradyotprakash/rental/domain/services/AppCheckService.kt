package com.project.pradyotprakash.rental.domain.services

interface AppCheckService {
    fun getAppCheckToken(
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit,
    )
}