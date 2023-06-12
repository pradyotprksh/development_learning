package com.pradyotprakash.jwitter.core.services

interface UserService {
    suspend fun createUser(
        email: String,
        password: String,
    )
}