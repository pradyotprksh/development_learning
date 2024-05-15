package com.pradyotprakash.xfullstack.data.user

/**
 * Data source for users.
 */
interface UserDataSource {
    /**
     * Get a user by their username.
     */
    suspend fun getUserByUsername(username: String): User?

    suspend fun getUserByEmailAddress(email: String): User?

    suspend fun getUserByPhoneNumber(phoneNumber: String): User?

    /**
     * Get a user by their id.
     */
    suspend fun getUserByUserId(userId: String): User?

    /**
     * Insert a new user in the data source.
     */
    suspend fun insertNewUser(user: User): Boolean

    suspend fun isUsernamePresent(username: String): Boolean

    suspend fun isEmailPresent(email: String): Boolean

    suspend fun isPhoneNumberPresent(phoneNumber: String): Boolean
}