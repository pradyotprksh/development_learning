package com.pradyotprakash.xfullstack.data.user

/**
 * Data source for users.
 */
interface UserDataSource {
    /**
     * Get a user by their username.
     */
    suspend fun getUserByUsername(username: String): User?

    /**
     * Get a user by their id.
     */
    suspend fun getUserByUserId(userId: String): User?

    /**
     * Insert a new user in the data source.
     */
    suspend fun insertNewUser(user: User): Boolean
}