package com.pradyotprakash.jwitter.data.services

import com.pradyotprakash.jwitter.core.models.RequestDetails
import com.pradyotprakash.jwitter.core.network.NetworkClient
import com.pradyotprakash.jwitter.core.services.UserService

class UserServiceImplementation(
    private val networkClient: NetworkClient,
): UserService {
    override suspend fun createUser(email: String, password: String) {
        networkClient.post<String>(
            RequestDetails(
                endpoint = "user",
                data = mapOf(
                    "email" to email,
                    "password" to password
                )
            ),
        )
    }
}